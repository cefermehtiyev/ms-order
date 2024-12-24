package az.ingress.service.abstraction;

import az.ingress.model.criteria.OrderCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.OrderRequest;
import az.ingress.model.response.OrderResponse;
import az.ingress.model.response.PageableOrderResponse;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
    PageableOrderResponse<OrderResponse> getAllOrders(PageCriteria pageCriteria, OrderCriteria orderCriteria);
    void cancelOrder(Long orderId);

}
