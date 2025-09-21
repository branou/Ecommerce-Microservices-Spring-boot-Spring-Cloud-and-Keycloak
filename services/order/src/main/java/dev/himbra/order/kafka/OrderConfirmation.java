package dev.himbra.order.kafka;

import dev.himbra.order.customer.CustomerResponse;
import dev.himbra.order.entities.PaymentMethod;
import dev.himbra.order.products.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products

) {
}
