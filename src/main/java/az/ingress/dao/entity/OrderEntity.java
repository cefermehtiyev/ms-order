package az.ingress.dao.entity;

import az.ingress.model.enums.DeliveryMethod;
import az.ingress.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderEntity {
    private Long id;
    private Long orderId;
    private Long userID;
    private BigDecimal totalAmount;
    private DeliveryMethod deliveryMethod;
    private Status status;
    private LocalDateTime OrderDate;

}
