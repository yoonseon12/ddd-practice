package dev.practice.order.application.order;

import org.springframework.stereotype.Service;

import dev.practice.order.domain.notification.NotificationService;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;
import dev.practice.order.domain.order.OrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {
	private final OrderService orderService;
	private final NotificationService notificationService;

	public String registerOrder(OrderCommand.RegisterOrderRequest command) {
		String orderToken = orderService.registerOrder(command);
		notificationService.sendKakao("ORDER_COMPLETE", "content");
		return orderToken;
	}

	public OrderInfo.RetrieveOrderResponse retrieveOrder(String orderToken) {
		return orderService.retrieveOrder(orderToken);
	}

	public void paymentOrder(OrderCommand.PaymentOrderRequest command) {
		orderService.paymentOrder(command);
		notificationService.sendKakao(null, null);
	}
}
