package dev.practice.order.domain.order;

import dev.practice.order.domain.order.item.OrderItem;
import dev.practice.order.domain.order.item.OrderItemOption;
import dev.practice.order.domain.order.item.OrderItemOptionGroup;

public interface OrderStore {
	Order store(Order order);
	OrderItem store(OrderItem orderItem);
	OrderItemOption store(OrderItemOption orderItemOption);
	OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);
}
