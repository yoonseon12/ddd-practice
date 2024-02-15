package dev.practice.order.infrastructure.order;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemReader;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderItemSeriesFactory;
import dev.practice.order.domain.order.OrderStore;
import dev.practice.order.domain.order.item.OrderItem;
import dev.practice.order.domain.order.item.OrderItemOption;
import dev.practice.order.domain.order.item.OrderItemOptionGroup;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {
	private final ItemReader itemReader;
	private final OrderStore orderStore;

	@Override
	public List<OrderItem> store(Order order, OrderCommand.RegisterOrderRequest command) {
		return command.getOrderItemList().stream()
			.map(orderItemRequest -> {
				Item item = itemReader.getItemBy(orderItemRequest.getItemToken());
				OrderItem initOrderItem = orderItemRequest.toEntity(order, item);
				OrderItem orderItem = orderStore.store(initOrderItem);

				orderItemRequest.getOrderItemOptionGroupList().forEach(orderItemOptionGroupRequest -> {
					OrderItemOptionGroup initOrderItemOptionGroup = orderItemOptionGroupRequest.toEntity(orderItem);
					OrderItemOptionGroup orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

					orderItemOptionGroupRequest.getOrderItemOptionList().forEach(orderItemOptionRequest -> {
						OrderItemOption orderItemOption = orderItemOptionRequest.toEntity(orderItemOptionGroup);
						orderStore.store(orderItemOption);
					});
				});
				return orderItem;
			})
			.toList();
	}
}
