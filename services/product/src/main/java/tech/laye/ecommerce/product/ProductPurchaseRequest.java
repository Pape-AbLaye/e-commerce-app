package tech.laye.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product is mandatory")
        Integer productId,
        @NotNull(message = "product is mandatory")
        double quantity
) {
}
