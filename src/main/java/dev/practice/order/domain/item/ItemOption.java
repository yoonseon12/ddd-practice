package dev.practice.order.domain.item;

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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_options")
public class ItemOption extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer ordering;
	private String itemOptionName;
	private Long itemOptionPrice;

	@ManyToOne
	@JoinColumn(name = "item_option_group_id")
	private ItemOptionGroup itemOptionGroup;

	@Builder
	public ItemOption(
		ItemOptionGroup itemOptionGroup,
		Integer ordering,
		String itemOptionName,
		Long itemOptionPrice
	) {
		validateItemOption(itemOptionGroup, ordering, itemOptionName, itemOptionPrice);

		this.itemOptionGroup = itemOptionGroup;
		this.ordering = ordering;
		this.itemOptionName = itemOptionName;
		this.itemOptionPrice = itemOptionPrice;
	}

	private void validateItemOption(ItemOptionGroup itemOptionGroup, Integer ordering, String itemOptionName,
		Long itemOptionPrice) {
		checkItemOptionGroup(itemOptionGroup);
		checkOrdering(ordering);
		checkItemOptionName(itemOptionName);
		checkItemOptionPrice(itemOptionPrice);
	}

	private void checkItemOptionPrice(Long itemOptionPrice) {
		if (itemOptionPrice == null)
			throw new InvalidParamException("ItemOption.itemOptionPrice");
	}

	private void checkItemOptionName(String itemOptionName) {
		if (StringUtils.isBlank(itemOptionName))
			throw new InvalidParamException("ItemOption.itemOptionName");
	}

	private void checkOrdering(Integer ordering) {
		if (ordering == null)
			throw new InvalidParamException("ItemOption.ordering");
	}

	private void checkItemOptionGroup(ItemOptionGroup itemOptionGroup) {
		if (itemOptionGroup == null)
			throw new InvalidParamException("ItemOption.itemOptionGroup");
	}
}
