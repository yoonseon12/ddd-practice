package dev.practice.order.infrastructure.item;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.practice.order.common.exception.EntityNotFoundException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemInfo;
import dev.practice.order.domain.item.ItemOption;
import dev.practice.order.domain.item.ItemOptionGroup;
import dev.practice.order.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {
	private final ItemRepository itemRepository;

	@Override
	public Item getItemBy(String itemTiken) {
		return itemRepository.findByItemToken(itemTiken)
			.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public List<ItemInfo.ItemOptionGroupInfo> getItemOptionSeries(Item item) {
		List<ItemOptionGroup> itemOptionGroupList = item.getItemOptionGroupList();

		return itemOptionGroupList.stream()
			.map(itemOptionGroup -> {
				List<ItemOption> itemOptionList = itemOptionGroup.getItemOptionList();
				List<ItemInfo.ItemOptionInfo> itemOptionInfoList = itemOptionList.stream()
					.map(ItemInfo.ItemOptionInfo::new)
					.toList();

				return new ItemInfo.ItemOptionGroupInfo(itemOptionGroup, itemOptionInfoList);
			})
			.toList();
	}

}
