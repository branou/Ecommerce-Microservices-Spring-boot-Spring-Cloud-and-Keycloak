package dev.himbra.payment.dtos;

import dev.himbra.payment.entities.Customer;
import dev.himbra.payment.entities.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
