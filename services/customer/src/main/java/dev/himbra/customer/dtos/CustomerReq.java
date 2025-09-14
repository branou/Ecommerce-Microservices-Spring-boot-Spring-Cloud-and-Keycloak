package dev.himbra.customer.dtos;

import dev.himbra.customer.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerReq(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstname,
        @NotNull(message = "Customer firstname is required")
        String lastname,
        @NotNull(message = "Customer Email is required")
        @Email(message = "Customer Email is not a valid email address")
        String email,
        Address address
) {
}
