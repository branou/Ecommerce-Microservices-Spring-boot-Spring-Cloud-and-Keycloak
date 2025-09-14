package dev.himbra.customer.mapper;

import dev.himbra.customer.dtos.CustomerReq;
import dev.himbra.customer.dtos.CustomerRes;
import dev.himbra.customer.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerReq request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerRes fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerRes(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
