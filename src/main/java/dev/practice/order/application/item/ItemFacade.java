package dev.practice.order.application.item;

import org.springframework.stereotype.Service;

import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import dev.practice.order.domain.item.ItemService;
import dev.practice.order.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemFacade {
	private final ItemService itemService;
	private final NotificationService notificationService;

	public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken) {
		String savedItemToken = itemService.registerItem(request, partnerToken);
		notificationService.sendEmail(null, null, null);

		return savedItemToken;
	}

	public void changeOnSalesItem(String itemToken) {
		itemService.changeOnSale(itemToken);
	}

	public void changeEndOfSaleItem(String itemToken) {
		itemService.changeEndOfSale(itemToken);
	}

	public ItemInfo.Main retrieveItemInfo(String itemToken) {
		return itemService.retrieveItemInfo(itemToken);
	}
}
