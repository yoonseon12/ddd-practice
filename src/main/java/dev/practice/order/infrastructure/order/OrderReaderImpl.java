package dev.practice.order.infrastructure.order;

import org.springframework.stereotype.Component;

import dev.practice.order.common.exception.EntityNotFoundException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {
	private final OrderRepository orderRepository;

	@Override
	public Order getOrder(String orderToken) {
		return orderRepository.findByOrderToken(orderToken)
			.orElseThrow(EntityNotFoundException::new);
	}
}
