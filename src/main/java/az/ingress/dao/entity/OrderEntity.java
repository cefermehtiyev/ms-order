package az.ingress.dao.entity;

import az.ingress.model.enums.DeliveryMethod;
import az.ingress.model.enums.OrderStatus;
import az.ingress.model.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "orders")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Long userId;
    private Long cardNumber;
    @Enumerated(STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(STRING)
    private DeliveryMethod deliveryMethod;
    @Enumerated(STRING)
    private OrderStatus status;
    @CreationTimestamp
    private LocalDateTime createDate;

    @OneToMany(
            fetch = LAZY,
            cascade = {MERGE,PERSIST,REMOVE},
            mappedBy = "order"
    )
    @ToString.Exclude
    List<OrderItemEntity> orderItemEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
