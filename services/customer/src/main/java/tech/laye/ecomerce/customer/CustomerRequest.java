package tech.laye.ecomerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "customer's firstname is required")
         String firstname,
         @NotNull(message = "customer's lastname is required")
         String lastname,
         @NotNull(message = "customer's email is required")
         @Email(message = "is not a valid Email")
         String email,
         Address address
) {
}
