package dev.himbra.order.payment;

import dev.himbra.order.customer.CustomerResponse;
import dev.himbra.order.entities.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
