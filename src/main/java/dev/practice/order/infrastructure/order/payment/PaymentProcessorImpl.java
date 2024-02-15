package dev.practice.order.infrastructure.order.payment;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.payment.PaymentProcessor;
import dev.practice.order.domain.order.payment.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {
    private final List<PaymentValidator> paymentValidatorList;
    private final List<PaymentApiCaller> paymentApiCallerList;

    @Override
    public void pay(Order order, OrderCommand.PaymentOrderRequest command) {
        paymentValidatorList.forEach(paymentValidator -> paymentValidator.validate(order, command));
        PaymentApiCaller payApiCaller = routingApiCaller(command);
        payApiCaller.pay(command);
    }

    private PaymentApiCaller routingApiCaller(OrderCommand.PaymentOrderRequest command) {
        return paymentApiCallerList.stream()
                .filter(paymentApiCaller -> paymentApiCaller.support(command.getPayMethod()))
                .findFirst()
                .orElseThrow(InvalidParamException::new);
    }
}
