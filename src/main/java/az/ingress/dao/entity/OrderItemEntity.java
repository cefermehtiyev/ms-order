package az.ingress.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "order_items")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long orderItemId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal pricePerUnit;

    @ManyToOne(
            cascade = {MERGE,PERSIST,REMOVE},
            fetch = LAZY
    )
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(orderItemId, that.orderItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderItemId);
    }
}
