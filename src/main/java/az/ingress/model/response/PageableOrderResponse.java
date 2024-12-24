package az.ingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableOrderResponse<T>{
    private List<T> orders;
    private Integer lastPageNumber;
    private Integer totalPage;
    private boolean hasNextPage;
}
