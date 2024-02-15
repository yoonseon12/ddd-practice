package dev.practice.order.domain.order.item;

import org.apache.commons.lang3.StringUtils;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_item_options")
public class OrderItemOption extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_item_option_group_id")
	private OrderItemOptionGroup orderItemOptionGroup;
	private Integer ordering;
	private String itemOptionName;
	private Long itemOptionPrice;

	@Builder
	public OrderItemOption(
		OrderItemOptionGroup orderItemOptionGroup,
		Integer ordering,
		String itemOptionName,
		Long itemOptionPrice) {
		validateOrderItemOption(orderItemOptionGroup, ordering, itemOptionName, itemOptionPrice);

		this.orderItemOptionGroup = orderItemOptionGroup;
		this.ordering = ordering;
		this.itemOptionName = itemOptionName;
		this.itemOptionPrice = itemOptionPrice;
	}

	private void validateOrderItemOption(OrderItemOptionGroup orderItemOptionGroup, Integer ordering, String itemOptionName,
		Long itemOptionPrice) {
		if (orderItemOptionGroup == null) throw new InvalidParamException();
		if (ordering == null) throw new InvalidParamException();
		if (StringUtils.isEmpty(itemOptionName)) throw new InvalidParamException();
		if (itemOptionPrice == null) throw new InvalidParamException();
	}
}
