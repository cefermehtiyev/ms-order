package az.ingress.service.concurate;

import az.ingress.client.ProductClient;
import az.ingress.dao.entity.OrderEntity;
import az.ingress.dao.entity.OrderItemEntity;
import az.ingress.dao.repository.AddressRepository;
import az.ingress.dao.repository.OrderRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.mapper.OrderItemMapper;
import az.ingress.model.criteria.OrderCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.dto.ProductIdsDto;
import az.ingress.model.request.OrderItemInfo;
import az.ingress.model.request.OrderRequest;
import az.ingress.model.response.PageableOrderResponse;
import az.ingress.service.abstraction.OrderService;
import az.ingress.service.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static az.ingress.exception.ErrorMessage.ORDER_NOT_FOUND;
import static az.ingress.mapper.AddressMapper.ADDRESS_MAPPER;
import static az.ingress.mapper.OrderItemMapper.ORDER_ITEM_MAPPER;
import static az.ingress.mapper.OrderMapper.ORDER_MAPPER;
import static az.ingress.model.enums.OrderStatus.CANCELLED;
import static az.ingress.model.enums.OrderStatus.PENDING;

@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductClient productClient;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        var order = ORDER_MAPPER.buildOrderEntity(orderRequest);

        var productList = productClient.getProduct(ProductIdsDto.builder()
                .productIds(orderRequest.getOrderItemInfos().stream().map(OrderItemInfo::getProductId).toList()).build());
        var orderItems = productList.stream().map(orderItem -> ORDER_ITEM_MAPPER.buildOrderItemEntity(order,orderItem)).toList();

        order.setOrderItemEntities(orderItems);
        calculateAmount(order);
        orderRepository.save(order);
        addressRepository.save(ADDRESS_MAPPER.buildAddressEntity(orderRequest.getAddressInfo()));

    }


    private void calculateAmount(OrderEntity orderEntity) {
        BigDecimal amount = BigDecimal.ZERO;

        for (OrderItemEntity orderItemEntity : orderEntity.getOrderItemEntities()) {
            amount = amount.add(orderItemEntity.getPricePerUnit().multiply(BigDecimal.valueOf(orderItemEntity.getQuantity())));
        }
        orderEntity.setAmount(amount);
    }

    @Override
    public PageableOrderResponse getAllOrders(PageCriteria pageCriteria, OrderCriteria orderCriteria) {
        var userPage = orderRepository.findAll(
                new OrderSpecification(orderCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending())
        );
        return ORDER_MAPPER.buildPageableOrderResponse(userPage);

    }

    private OrderEntity fetchOrderExist(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(ORDER_NOT_FOUND.getMessage())
        );
    }

    @Override
    public void cancelOrder(Long orderId) {
        var order = fetchOrderExist(orderId);
        if (order.getStatus() == PENDING) {
            order.setStatus(CANCELLED);
            orderRepository.save(order);
        }

    }
}
