package az.ingress.controller;

import az.ingress.model.criteria.OrderCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.OrderRequest;
import az.ingress.model.response.PageableOrderResponse;
import az.ingress.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }

    @GetMapping
    public PageableOrderResponse getAllOrders(PageCriteria pageCriteria, OrderCriteria orderCriteria){
        return orderService.getAllOrders(pageCriteria, orderCriteria);
    }
}
