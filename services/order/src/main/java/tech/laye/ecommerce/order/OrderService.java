package tech.laye.ecommerce.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.laye.ecommerce.customer.CustomerClient;
import tech.laye.ecommerce.exception.BusinessException;
import tech.laye.ecommerce.kafka.OrderConfirmation;
import tech.laye.ecommerce.kafka.OrderProducer;
import tech.laye.ecommerce.orderLine.OrderLineRequest;
import tech.laye.ecommerce.orderLine.OrderLineService;
import tech.laye.ecommerce.payment.PaymentClient;
import tech.laye.ecommerce.payment.PaymentRequest;
import tech.laye.ecommerce.product.ProductClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder( OrderRequest request) {
        //customer exist ? (using FeignClient)
        var customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(
                () -> new BusinessException("No customer exists with the provided ID")
        );

        //purchase the product (using RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        var order = orderRepository.save(mapper.toOrder(request));

        //persist order line
        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation ->  notification (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(
                        () -> new EntityNotFoundException("order not found !")
                );
    }
}
