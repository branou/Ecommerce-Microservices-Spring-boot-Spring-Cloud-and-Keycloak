package dev.himbra.order.service;

import dev.himbra.order.dtos.OrderLineRequest;
import dev.himbra.order.dtos.OrderResponse;
import dev.himbra.order.products.ProductClient;
import dev.himbra.order.customer.CustomerClient;
import dev.himbra.order.dtos.OrderRequest;
import dev.himbra.order.mapper.OrderMapper;
import dev.himbra.order.products.PurchaseRequest;
import dev.himbra.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    public Integer createOrder(OrderRequest orderRequest){
        // Check if customer exists
        var customer = this.customerClient.getCustomerById(orderRequest.id())
                .orElseThrow(()->new IllegalArgumentException("Customer not found"));
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());
        var order =  this.orderRepository.save(this.orderMapper.toOrder(orderRequest));
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        return order.getId();
    }
    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(this.orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(this.orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
