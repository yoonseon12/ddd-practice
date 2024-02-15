package dev.practice.order.domain.order;

public interface OrderService {
	String registerOrder(OrderCommand.RegisterOrderRequest command);
	OrderInfo.RetrieveOrderResponse retrieveOrder(String orderToken);
	void paymentOrder(OrderCommand.PaymentOrderRequest command);
}
