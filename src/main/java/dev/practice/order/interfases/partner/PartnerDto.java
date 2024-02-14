package dev.practice.order.interfases.partner;

import dev.practice.order.domain.partner.Partner;
import dev.practice.order.domain.partner.PartnerInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartnerDto {

	@Getter
	@Setter
	@ToString
	public static class RegisterRequest {
		@NotEmpty(message = "partnerName 는 필수값 입니다")
		private String partnerName;

		@NotEmpty(message = "businessNo 는 필수값 입니다")
		private String businessNo;

		@Email(message = "email 형식에 맞아야 합니다")
		@NotEmpty(message = "email 는 필수값 입니다")
		private String email;

		/* MapStructor로 해결 가능
		public PartnerCommand toCommand() {
			return PartnerCommand.builder()
				.partnerName(partnerName)
				.businessNo(businessNo)
				.email(email)
				.build();
		}
		*/
	}

	@Getter
	@ToString
	public static class RegisterResponse {
		private final String partnerToken;
		private final String partnerName;
		private final String businessNo;
		private final String email;
		private final Partner.Status status;

		public RegisterResponse(PartnerInfo partnerInfo) {
			this.partnerToken = partnerInfo.getPartnerToken();
			this.partnerName = partnerInfo.getPartnerName();
			this.businessNo = partnerInfo.getBusinessNo();
			this.email = partnerInfo.getEmail();
			this.status = partnerInfo.getStatus();
		}
	}
}
