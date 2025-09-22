package dev.himbra.notification.kafka;

import dev.himbra.notification.email.EmailService;
import dev.himbra.notification.entities.Notification;
import dev.himbra.notification.entities.NotificationType;
import dev.himbra.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmation(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", orderConfirmation));
        var notification = Notification.builder().notificationDate(LocalDateTime.now())
                .type(NotificationType.ORDER_CONFIRMATION)
                .orderConfirmation(orderConfirmation).build();
        notificationRepository.save(notification);

        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmation(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        var notification = Notification.builder().notificationDate(LocalDateTime.now())
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                                .paymentConfirmation(paymentConfirmation).build();
        notificationRepository.save(notification);

        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

}
