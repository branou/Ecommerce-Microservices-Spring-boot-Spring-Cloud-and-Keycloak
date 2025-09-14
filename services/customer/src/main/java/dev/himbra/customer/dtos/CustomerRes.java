package dev.himbra.customer.dtos;

import dev.himbra.customer.entities.Address;

public record CustomerRes(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
