package dev.practice.order.interfases.order;

import java.time.ZonedDateTime;
import java.util.List;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderInfo;
import dev.practice.order.domain.order.item.OrderItem;
import dev.practice.order.domain.order.item.OrderItemOption;
import dev.practice.order.domain.order.item.OrderItemOptionGroup;
import dev.practice.order.domain.order.payment.PayMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {

	@Getter
	@Builder
	public static class RegisterOrderRequest {
		@NotNull(message = "userId 는 필수값입니다")
		private Long userId;

		@NotBlank(message = "payMethod 는 필수값입니다")
		private String payMethod;

		@NotBlank(message = "receiverName 는 필수값입니다")
		private String receiverName;

		@NotBlank(message = "receiverPhone 는 필수값입니다")
		private String receiverPhone;

		@NotBlank(message = "receiverZipcode 는 필수값입니다")
		private String receiverZipcode;

		@NotBlank(message = "receiverAddress1 는 필수값입니다")
		private String receiverAddress1;

		@NotBlank(message = "receiverAddress2 는 필수값입니다")
		private String receiverAddress2;

		@NotBlank(message = "etcMessage 는 필수값입니다")
		private String etcMessage;

		private List<RegisterOrderItemRequest> orderItemList;
	}

	@Getter
	@Setter
	@ToString
	public static class RegisterOrderItemRequest {
		@NotNull(message = "orderCount 는 필수값입니다")
		private Integer orderCount;

		@NotBlank(message = "itemToken 는 필수값입니다")
		private String itemToken;

		@NotBlank(message = "itemName 는 필수값입니다")
		private String itemName;

		@NotNull(message = "itemPrice 는 필수값입니다")
		private Long itemPrice;

		private List<RegisterOrderItemOptionGroupRequest> orderItemOptionGroupList;
	}

	@Getter
	@Builder
	public static class RegisterOrderItemOptionGroupRequest {
		@NotNull(message = "ordering 는 필수값입니다")
		private Integer ordering;

		@NotBlank(message = "itemOptionGroupName 는 필수값입니다")
		private String itemOptionGroupName;

		private List<RegisterOrderItemOptionRequest> orderItemOptionList;
	}

	@Getter
	@Builder
	public static class RegisterOrderItemOptionRequest {
		@NotNull(message = "ordering 는 필수값입니다")
		private Integer ordering;

		@NotBlank(message = "itemOptionName 는 필수값입니다")
		private String itemOptionName;

		@NotNull(message = "itemOptionPrice 는 필수값입니다")
		private Long itemOptionPrice;
	}

	@Getter
	@Builder
	public static class RegisterOrderResponse {
		private final String orderToken;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class RetrieveOrderResponse {
		private final Long orderId;
		private final String orderToken;
		private final Long userId;
		private final String payMethod;
		private final Long totalAmount;
		private final OrderInfo.DeliveryInfo deliveryInfo;
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
			this.deliveryInfo = new OrderInfo.DeliveryInfo(order.getDeliveryFragment());
			this.orderedAt = order.getOrderedAt();
			this.status = order.getStatus().name();
			this.statusDescription = order.getStatus().getDescription();
			this.orderItemResponseList = order.getOrderItemList().stream()
				.map(OrderItemResponse::new)
				.toList();
		}
	}

	@Getter
	@Builder
	@AllArgsConstructor
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
	@Builder
	@AllArgsConstructor
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
	@Builder
	@AllArgsConstructor
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
	public static class PaymentOrderRequest {
		@NotBlank(message = "orderToken 는 필수값입니다")
		private String orderToken;

		@NotNull(message = "payMethod 는 필수값입니다")
		private PayMethod payMethod;

		@NotNull(message = "amount 는 필수값입니다")
		private Long amount;

		@NotBlank(message = "orderDescription 는 필수값입니다")
		private String orderDescription;
	}
}
