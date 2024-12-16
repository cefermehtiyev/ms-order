package az.ingress.model.response;

import az.ingress.model.enums.DeliveryMethod;
import az.ingress.model.enums.OrderStatus;
import az.ingress.model.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private OrderStatus orderStatus;
    private LocalDateTime createDate;
}
