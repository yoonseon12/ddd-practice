package dev.practice.order.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.practice.order.domain.order.item.OrderItemOption;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
