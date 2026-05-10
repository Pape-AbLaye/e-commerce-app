package tech.laye.ecommerce.orderLine;

import org.springframework.stereotype.Service;
import tech.laye.ecommerce.order.Order;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine
                .builder()
                .id(request.id())
                .order(
                        Order
                                .builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
