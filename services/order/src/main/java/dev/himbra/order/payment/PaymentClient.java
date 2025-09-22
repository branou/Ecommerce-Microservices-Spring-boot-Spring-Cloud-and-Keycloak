package dev.himbra.order.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${application.config.customer-url}")
public interface PaymentClient {
    @PostMapping
    Integer createPayment(@RequestBody PaymentRequest request);
}
