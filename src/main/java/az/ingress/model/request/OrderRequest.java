package az.ingress.model.request;

import az.ingress.model.enums.DeliveryMethod;
import az.ingress.model.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private Long userId;
    private Long cardNumber;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private AddressInfo addressInfo;
    private List<OrderItemInfo> orderItemInfos;
}
