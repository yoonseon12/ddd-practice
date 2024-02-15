package dev.practice.order.domain.order;

import java.time.ZonedDateTime;
import java.util.List;

import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.item.OrderItem;
import dev.practice.order.domain.order.item.OrderItemOption;
import dev.practice.order.domain.order.item.OrderItemOptionGroup;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderInfo {
	@Getter
	public static class RetrieveOrderResponse {
		private final Long orderId;
		private final String orderToken;
		private final Long userId;
		private final String payMethod;
		private final Long totalAmount;
		private final DeliveryInfo deliveryInfo;
		private final ZonedDateTime orderedAt;
		private final String status;
		private final String statusDescription;
		private final List<OrderItemResponse> orderItemResponseList;

		public RetrieveOrderResponse(Order order) {
			this.orderId = order.getId();
			this.orderToken = order.getOrderToken();
			this.userId = order.getUserId();
			this.payMethod = order.getPayMethod();
			this.totalAmount = order.calculateTotalAmount();
			this.deliveryInfo = new DeliveryInfo(order.getDeliveryFragment());
			this.orderedAt = order.getOrderedAt();
			this.status = order.getStatus().name();
			this.statusDescription = order.getStatus().getDescription();
			this.orderItemResponseList = order.getOrderItemList().stream()
				.map(OrderItemResponse::new)
				.toList();
		}
	}

	@Getter
	public static class OrderItemResponse {
		private final Integer orderCount;
		private final Long partnerId;
		private final Long itemId;
		private final String itemName;
		private final Long totalAmount;
		private final Long itemPrice;
		private final String deliveryStatus;
		private final String deliveryStatusDescription;
		private final List<OrderItemOptionGroupResponse> orderItemOptionGroupList;

		public OrderItemResponse(OrderItem orderItem) {
			this.orderCount = orderItem.getOrderCount();
			this.partnerId = orderItem.getPartnerId();
			this.itemId = orderItem.getItemId();
			this.itemName = orderItem.getItemName();
			this.totalAmount = orderItem.calculateTotalAmount();
			this.itemPrice = orderItem.getItemPrice();
			this.deliveryStatus = orderItem.getDeliveryStatus().name();
			this.deliveryStatusDescription = orderItem.getDeliveryStatus().getDescription();
			this.orderItemOptionGroupList = orderItem.getOrderItemOptionGroupList().stream()
				.map(OrderItemOptionGroupResponse::new)
				.toList();
		}
	}

	@Getter
	public static class OrderItemOptionGroupResponse {
		private final Integer ordering;
		private final String itemOptionGroupName;
		private final List<OrderItemOptionResponse> orderItemOptionList;

		public OrderItemOptionGroupResponse(OrderItemOptionGroup orderItemOptionGroup) {
			this.ordering = orderItemOptionGroup.getOrdering();
			this.itemOptionGroupName = orderItemOptionGroup.getItemOptionGroupName();
			this.orderItemOptionList = orderItemOptionGroup.getOrderItemOptionList().stream()
				.map(OrderItemOptionResponse::new)
				.toList();
		}
	}

	@Getter
	public static class OrderItemOptionResponse {
		private final Integer ordering;
		private final String itemOptionName;
		private final Long itemOptionPrice;

		public OrderItemOptionResponse(OrderItemOption orderItemOption) {
			this.ordering = orderItemOption.getOrdering();
			this.itemOptionName = orderItemOption.getItemOptionName();
			this.itemOptionPrice = orderItemOption.getItemOptionPrice();
		}
	}

	@Getter
	public static class DeliveryInfo {
		private final String receiverName;
		private final String receiverPhone;
		private final String receiverZipcode;
		private final String receiverAddress1;
		private final String receiverAddress2;
		private final String etcMessage;

		public DeliveryInfo(DeliveryFragment deliveryFragment) {
			this.receiverName = deliveryFragment.getReceiverName();
			this.receiverPhone = deliveryFragment.getReceiverPhone();
			this.receiverZipcode = deliveryFragment.getReceiverZipcode();
			this.receiverAddress1 = deliveryFragment.getReceiverAddress1();
			this.receiverAddress2 = deliveryFragment.getReceiverAddress2();
			this.etcMessage = deliveryFragment.getEtcMessage();
		}
	}
}
