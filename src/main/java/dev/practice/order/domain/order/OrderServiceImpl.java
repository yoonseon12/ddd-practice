package dev.practice.order.domain.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.practice.order.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderStore orderStore;
	private final OrderReader orderReader;
	private final OrderItemSeriesFactory orderItemSeriesFactory;
	private final PaymentProcessor paymentProcessor;

	@Override
	@Transactional
	public String registerOrder(OrderCommand.RegisterOrderRequest command) {
		Order order = orderStore.store(command.toEntity());
		orderItemSeriesFactory.store(order, command);
		return order.getOrderToken();
	}

	@Override
	public OrderInfo.RetrieveOrderResponse retrieveOrder(String orderToken) {
		Order order = orderReader.getOrder(orderToken);
		return new OrderInfo.RetrieveOrderResponse(order);
	}

	@Override
	@Transactional
	public void paymentOrder(OrderCommand.PaymentOrderRequest command) {
		String orderToken = command.getOrderToken();
		Order order = orderReader.getOrder(orderToken);
		paymentProcessor.pay(order, command);
		order.orderComplete();
	}
}
