package dev.practice.order.domain.order;

import java.util.List;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.order.fragment.DeliveryFragment;
import dev.practice.order.domain.order.item.OrderItem;
import dev.practice.order.domain.order.item.OrderItemOption;
import dev.practice.order.domain.order.item.OrderItemOptionGroup;
import dev.practice.order.domain.order.payment.PayMethod;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCommand {

	@Getter
	@Builder
	public static class RegisterOrderRequest {
		private final Long userId;
		private final String payMethod;
		private final String receiverName;
		private final String receiverPhone;
		private final String receiverZipcode;
		private final String receiverAddress1;
		private final String receiverAddress2;
		private final String etcMessage;
		private final List<RegisterOrderItemRequest> orderItemList;

		public Order toEntity() {
			DeliveryFragment deliveryFragment = DeliveryFragment.builder()
				.receiverName(receiverName)
				.receiverPhone(receiverPhone)
				.receiverZipcode(receiverZipcode)
				.receiverAddress1(receiverAddress1)
				.receiverAddress2(receiverAddress2)
				.etcMessage(etcMessage)
				.build();

			return Order.builder()
				.userId(userId)
				.payMethod(payMethod)
				.deliveryFragment(deliveryFragment)
				.build();
		}
	}

	@Getter
	@Builder
	public static class RegisterOrderItemRequest {
		private final Integer orderCount;
		private final String itemToken;
		private final String itemName;
		private final Long itemPrice;
		private final List<RegisterOrderItemOptionGroupRequest> orderItemOptionGroupList;

		public OrderItem toEntity(Order order, Item item) {
			return OrderItem.builder()
				.order(order)
				.orderCount(orderCount)
				.partnerId(item.getPartnerId())
				.itemId(item.getId())
				.itemToken(itemToken)
				.itemName(itemName)
				.itemPrice(itemPrice)
				.build();
		}
	}

	@Getter
	@Builder
	public static class RegisterOrderItemOptionGroupRequest {
		private final Integer ordering;
		private final String itemOptionGroupName;
		private final List<RegisterOrderItemOptionRequest> orderItemOptionList;

		public OrderItemOptionGroup toEntity(OrderItem orderItem) {
			return OrderItemOptionGroup.builder()
				.orderItem(orderItem)
				.ordering(ordering)
				.itemOptionGroupName(itemOptionGroupName)
				.build();
		}
	}

	@Getter
	@Builder
	public static class RegisterOrderItemOptionRequest {
		private final Integer ordering;
		private final String itemOptionName;
		private final Long itemOptionPrice;

		public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup) {
			return OrderItemOption.builder()
				.orderItemOptionGroup(orderItemOptionGroup)
				.ordering(ordering)
				.itemOptionName(itemOptionName)
				.itemOptionPrice(itemOptionPrice)
				.build();
		}
	}

	@Getter
	@Builder
	public static class PaymentOrderRequest {
		private final String orderToken;
		private final Long amount;
		private final PayMethod payMethod;
	}
}
