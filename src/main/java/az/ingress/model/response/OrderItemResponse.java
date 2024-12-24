package az.ingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Long orderId;
    private Long orderItemId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
}
