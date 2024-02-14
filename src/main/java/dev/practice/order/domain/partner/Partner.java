package dev.practice.order.domain.partner;

import org.apache.commons.lang3.StringUtils;

import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "partners")
public class Partner extends AbstractEntity {
	private static final String PREFIX_PARTNER = "ptn_";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String partnerToken;
	private String partnerName;
	private String businessNo;
	private String email;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Getter
	@RequiredArgsConstructor
	public enum Status {
		ENABLE("활성화"),
		DISABLE("비활성화");

		private final String description;
	}

	@Builder
	public Partner(String partnerName, String businessNo, String email) {
		validatePartner(partnerName, businessNo, email);
		this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
		this.partnerName = partnerName;
		this.businessNo = businessNo;
		this.email = email;
		this.status = Status.ENABLE;
	}

	private void validatePartner(String partnerName, String businessNo, String email) {
		checkPartnerName(partnerName);
		checkBusinessNo(businessNo);
		checkEmail(email);
	}

	private void checkEmail(String email) {
		if (StringUtils.isEmpty(email))
			throw new IllegalArgumentException("empty email");
	}

	private void checkBusinessNo(String businessNo) {
		if (StringUtils.isEmpty(businessNo))
			throw new IllegalArgumentException("empty businessNo");
	}

	private void checkPartnerName(String partnerName) {
		if (StringUtils.isEmpty(partnerName))
			throw new IllegalArgumentException("empty partnerName");
	}

	public void disable() {
		this.status = Status.DISABLE;
	}

	public void enable() {
		this.status = Status.ENABLE;
	}
}
