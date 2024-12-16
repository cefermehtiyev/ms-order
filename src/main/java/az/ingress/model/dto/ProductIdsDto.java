package az.ingress.model.dto;

import az.ingress.model.request.OrderItemInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductIdsDto {
    List<Long> productIds;
}
