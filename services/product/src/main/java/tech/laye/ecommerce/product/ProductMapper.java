package tech.laye.ecommerce.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.laye.ecommerce.category.Category;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    public Product toProduct( ProductRequest request) {
        return Product
                .builder()
                .id(request.id())
                .price(request.price())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder().id(request.id()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()

        );

    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product,double quantity) {
        return  new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
