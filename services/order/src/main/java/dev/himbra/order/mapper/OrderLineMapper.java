package dev.himbra.order.mapper;

import dev.himbra.order.dtos.OrderLineRequest;
import dev.himbra.order.dtos.OrderLineResponse;
import dev.himbra.order.entities.Order;
import dev.himbra.order.entities.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
