package dev.practice.order.interfases.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.practice.order.application.order.OrderFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderApiController {
	private final OrderFacade orderFacade;
	private final OrderDtoMapper orderDtoMapper;

	@PostMapping("/init")
	public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request) {
		OrderCommand.RegisterOrderRequest command = orderDtoMapper.of(request);
		String orderToken = orderFacade.registerOrder(command);
		OrderDto.RegisterOrderResponse response = orderDtoMapper.of(orderToken);

		return CommonResponse.success(response);
	}

	@GetMapping("/{orderToken}")
	public CommonResponse retrieveOrder(@PathVariable String orderToken) {
		OrderInfo.RetrieveOrderResponse info = orderFacade.retrieveOrder(orderToken);
		OrderDto.RetrieveOrderResponse response = orderDtoMapper.of(info);

		return CommonResponse.success(response);
	}

	@PostMapping("/payment-order")
	public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentOrderRequest paymentRequest) {
		OrderCommand.PaymentOrderRequest command = orderDtoMapper.of(paymentRequest);
		orderFacade.paymentOrder(command);
		return CommonResponse.success();
	}
}