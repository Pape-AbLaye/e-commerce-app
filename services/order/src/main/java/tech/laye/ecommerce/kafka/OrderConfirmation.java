package tech.laye.ecommerce.kafka;

import tech.laye.ecommerce.customer.CustomerResponse;
import tech.laye.ecommerce.order.PaymentMethod;
import tech.laye.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
