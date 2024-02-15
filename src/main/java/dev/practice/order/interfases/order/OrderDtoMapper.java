package dev.practice.order.interfases.order;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {
	OrderCommand.RegisterOrderRequest of(OrderDto.RegisterOrderRequest request);
	OrderDto.RegisterOrderResponse of(String orderToken);
	OrderDto.RetrieveOrderResponse of(OrderInfo.RetrieveOrderResponse info);
	OrderCommand.PaymentOrderRequest of(OrderDto.PaymentOrderRequest request);
}
