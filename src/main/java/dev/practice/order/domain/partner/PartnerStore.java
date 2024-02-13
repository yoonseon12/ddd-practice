package dev.practice.order.domain.partner;

public interface PartnerStore {
	Partner store(Partner initPartner);
}
// 외부에서 전달된 요청을 해석해서 뒷단에 어플리케이션이나 도메인쪽을 호출해서 실행된 도메인의 결과물을 반환해서 호출한 쪽에서 원하는 응답 형태로 변환해서 리턴
