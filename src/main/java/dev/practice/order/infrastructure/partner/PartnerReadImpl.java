package dev.practice.order.infrastructure.partner;

import org.springframework.stereotype.Component;

import dev.practice.order.common.exception.EntityNotFoundException;
import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReadImpl implements PartnerReader {
	private final PartnerRepository partnerRepository;

	@Override
	public Partner getPartner(String partnerToken) {
		return partnerRepository.findByPartnerToken(partnerToken)
			.orElseThrow(EntityNotFoundException::new);
	}
}
