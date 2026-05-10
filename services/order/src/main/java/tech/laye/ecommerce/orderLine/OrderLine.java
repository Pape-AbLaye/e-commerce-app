package tech.laye.ecommerce.orderLine;

import jakarta.persistence.*;
import lombok.*;
import tech.laye.ecommerce.order.Order;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;

}
