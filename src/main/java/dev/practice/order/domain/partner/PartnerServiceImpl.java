package dev.practice.order.domain.partner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartnerServiceImpl implements PartnerService {
	private final PartnerStore partnerStore;
	private final PartnerReader partnerReader;

	@Override
	@Transactional
	public PartnerInfo registerPartner(PartnerCommand command) {
		var initPartner = command.toEntity();
		Partner partner = partnerStore.store(initPartner);

		return new PartnerInfo(partner);
	}

	@Override
	public PartnerInfo getPartnerInfo(String partnerToken) {
		Partner partner = partnerReader.getPartner(partnerToken);

		return new PartnerInfo(partner);
	}

	@Override
	@Transactional
	public PartnerInfo enablePartner(String partnerToken) {
		Partner partner = partnerReader.getPartner(partnerToken);
		partner.enable();

		return new PartnerInfo(partner);
	}

	@Override
	@Transactional
	public PartnerInfo disablePartner(String partnerToken) {
		Partner partner = partnerReader.getPartner(partnerToken);
		partner.disable();

		return new PartnerInfo(partner);
	}
}
