package dev.practice.order.domain.order;

import java.util.List;

import dev.practice.order.domain.order.item.OrderItem;

public interface OrderItemSeriesFactory {
    List<OrderItem> store(Order order, OrderCommand.RegisterOrderRequest command);
}
