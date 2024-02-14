package dev.practice.order.infrastructure.item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.practice.order.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByItemToken(String itemToken);
}
