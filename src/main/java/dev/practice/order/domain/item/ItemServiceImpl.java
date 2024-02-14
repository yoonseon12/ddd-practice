package dev.practice.order.domain.item;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final PartnerReader partnerReader;
	private final ItemStore itemStore;
	private final ItemReader itemReader;

	@Override
	@Transactional
	public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
		Partner findPartner = partnerReader.getPartner(partnerToken);
		Item initItem = command.toEntity(findPartner.getId());
		Item savedItem = itemStore.store(initItem);

		return savedItem.getItemToken();
	}

	@Override
	@Transactional
	public void changeOnSale(String itemToken) {
		Item findItem = itemReader.getItemBy(itemToken);
		findItem.changeOnSale();
	}

	@Override
	@Transactional
	public void changeEndOfSale(String itemToken) {
		Item findItem = itemReader.getItemBy(itemToken);
		findItem.changeEndOfSale();
	}

	@Override
	public ItemInfo.Main retrieveItemInfo(String itemToken) {
		Item findItem = itemReader.getItemBy(itemToken);
		List<ItemInfo.ItemOptionGroupInfo> findItemOptionGroupInfoList = itemReader.getItemOptionSeries(findItem);

		return new ItemInfo.Main(findItem, findItemOptionGroupInfoList);
	}
}
