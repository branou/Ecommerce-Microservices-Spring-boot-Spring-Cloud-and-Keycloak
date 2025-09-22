package dev.himbra.notification.kafka;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
