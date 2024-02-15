package dev.practice.order.infrastructure.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.practice.order.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findByOrderToken(String orderToken);
}
