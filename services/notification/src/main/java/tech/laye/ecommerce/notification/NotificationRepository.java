package tech.laye.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.laye.ecommerce.kafka.payment.PaymentConfirmation;

public interface NotificationRepository extends MongoRepository<Notification , String> {
}
