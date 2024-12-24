package az.ingress.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCriteria {
    private BigDecimal amountFrom;
    private BigDecimal amountTo;


}
