package dev.practice.order.domain.order.item;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item_option_groups")
public class OrderItemOptionGroup extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_item_id")
	private OrderItem orderItem;
	private Integer ordering;
	private String itemOptionGroupName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemOptionGroup", cascade = CascadeType.PERSIST)
	private List<OrderItemOption> orderItemOptionList = Lists.newArrayList();

	@Builder
	public OrderItemOptionGroup(
		OrderItem orderItem,
		Integer ordering,
		String itemOptionGroupName
	) {
		validateOrderItemOptionGroup(orderItem, ordering, itemOptionGroupName);

		this.orderItem = orderItem;
		this.ordering = ordering;
		this.itemOptionGroupName = itemOptionGroupName;
	}

	private void validateOrderItemOptionGroup(OrderItem orderItem, Integer ordering, String itemOptionGroupName) {
		if (orderItem == null) throw new InvalidParamException();
		if (ordering == null) throw new InvalidParamException();
		if (StringUtils.isEmpty(itemOptionGroupName)) throw new InvalidParamException();
	}

	public Long calculateTotalAmount() {
		return orderItemOptionList.stream()
			.mapToLong(OrderItemOption::getItemOptionPrice)
			.sum();
	}
}
