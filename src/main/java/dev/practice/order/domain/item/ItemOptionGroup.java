package dev.practice.order.domain.item;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "item_option_groups")
public class ItemOptionGroup extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	private Integer ordering;
	private String itemOptionGroupName;

	@OneToMany(mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
	private List<ItemOption> itemOptionList = Lists.newArrayList();

	@Builder
	public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
		validation(item, ordering, itemOptionGroupName);
		this.item = item;
		this.ordering = ordering;
		this.itemOptionGroupName = itemOptionGroupName;
	}

	private void validation(Item item, Integer ordering, String itemOptionGroupName) {
		checkItem(item);
		checkOrdering(ordering);
		checkItemOptionGroupName(itemOptionGroupName);
	}

	private void checkItemOptionGroupName(String itemOptionGroupName) {
		if (StringUtils.isBlank(itemOptionGroupName))
			throw new InvalidParamException("ItemOptionGroup.itemOptionGroupName");
	}

	private void checkOrdering(Integer ordering) {
		if (ordering == null)
			throw new InvalidParamException("ItemOptionGroup.ordering");
	}

	private void checkItem(Item item) {
		if (item == null)
			throw new InvalidParamException("ItemOptionGroup.item");
	}

	public ItemOptionGroup addItemOption(ItemOption itemOption) {
		this.itemOptionList.add(itemOption);
		return this;
	}

}
