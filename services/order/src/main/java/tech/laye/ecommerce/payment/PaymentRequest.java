package tech.laye.ecommerce.payment;

import tech.laye.ecommerce.customer.CustomerResponse;
import tech.laye.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
