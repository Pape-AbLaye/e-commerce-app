package tech.laye.ecommerce.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.laye.ecommerce.notification.NotificationProducer;
import tech.laye.ecommerce.notification.PaymentNotificationRequest;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment( PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(mapper.toPayment(paymentRequest));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()
                )
        );
        return payment.getId();
    }
}
