package dev.himbra.order.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product-service", url ="${application.config.product-url}")
public interface ProductClient {
    @PostMapping("/purchase")
    List<ProductPurchaseResponse> purchaseProducts(List<PurchaseRequest> request);
}
