package dev.practice.order.domain.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "items")
public class Item extends AbstractEntity {
	private static final String PREFIX_ITEM = "itm_";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String itemToken;
	private Long partnerId;
	private String itemName;
	private Long itemPrice;

	@OneToMany(mappedBy = "item",cascade = CascadeType.PERSIST)
	private List<ItemOptionGroup> itemOptionGroupList = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private Status status;

	@Getter
	@RequiredArgsConstructor
	public enum Status {
		PREPARE("판매준비중"),
		ON_SALES("판매중"),
		END_OF_SALES("판매종료");

		private final String description;
	}

	@Builder
	public Item(Long partnerId, String itemName, Long itemPrice) {
		validateItem(partnerId, itemName, itemPrice);

		this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
		this.partnerId = partnerId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.status = Status.PREPARE;
	}

	private void validateItem(Long partnerId, String itemName, Long itemPrice) {
		checkPartnerId(partnerId);
		checkItemName(itemName);
		checkItemPrice(itemPrice);
	}

	private void checkPartnerId(Long partnerId) {
		if (partnerId == null)
			throw new InvalidParamException();
	}

	private void checkItemName(String itemName) {
		if (StringUtils.isEmpty(itemName))
			throw new InvalidParamException();
	}

	private void checkItemPrice(Long itemPrice) {
		if (itemPrice == null)
			throw new InvalidParamException();
	}

	public void changePrepare() {
		this.status = Status.PREPARE;
	}

	public void changeOnSale() {
		this.status = Status.ON_SALES;
	}

	public void changeEndOfSale() {
		this.status = Status.END_OF_SALES;
	}
}
