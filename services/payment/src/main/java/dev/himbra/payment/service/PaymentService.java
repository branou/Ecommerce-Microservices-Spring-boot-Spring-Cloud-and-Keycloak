package dev.himbra.payment.service;

import dev.himbra.payment.dtos.PaymentRequest;
import dev.himbra.payment.kafka.NotificationProducer;
import dev.himbra.payment.kafka.PaymentNotification;
import dev.himbra.payment.mapper.PaymentMapper;
import dev.himbra.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = this.repository.save(this.mapper.toPayment(request));
        this.notificationProducer.sendPaymentNotification(
                new PaymentNotification(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
