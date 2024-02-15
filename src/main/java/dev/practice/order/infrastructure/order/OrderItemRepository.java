package dev.practice.order.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.practice.order.domain.order.item.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
