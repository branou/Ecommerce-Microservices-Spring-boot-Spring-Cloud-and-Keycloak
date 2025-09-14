package dev.himbra.order.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.himbra.order.entities.PaymentMethod;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
