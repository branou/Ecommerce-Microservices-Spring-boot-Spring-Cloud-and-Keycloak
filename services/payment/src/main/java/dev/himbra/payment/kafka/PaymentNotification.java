package dev.himbra.payment.kafka;

import dev.himbra.payment.entities.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotification(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
