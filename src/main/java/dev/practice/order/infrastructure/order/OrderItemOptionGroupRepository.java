package dev.practice.order.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.practice.order.domain.order.item.OrderItemOptionGroup;

public interface OrderItemOptionGroupRepository extends JpaRepository<OrderItemOptionGroup, Long> {
}
