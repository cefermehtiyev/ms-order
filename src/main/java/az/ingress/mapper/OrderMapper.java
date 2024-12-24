package az.ingress.mapper;

import az.ingress.dao.entity.OrderEntity;
import az.ingress.model.dto.ProductIdsDto;
import az.ingress.model.request.OrderRequest;
import az.ingress.model.response.OrderResponse;
import az.ingress.model.response.PageableOrderResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Collections;

import static az.ingress.model.enums.OrderStatus.PENDING;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity buildOrderEntity(OrderRequest orderRequest){
        return OrderEntity.builder()
                .userId(orderRequest.getUserId())
                .amount(BigDecimal.ZERO)
                .cardNumber(orderRequest.getCardNumber())
                .paymentMethod(orderRequest.getPaymentMethod())
                .deliveryMethod(orderRequest.getDeliveryMethod())
                .status(PENDING)
                .build();
    }

    public OrderResponse buildOrderResponse (OrderEntity orderEntity){
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .amount(orderEntity.getAmount())
                .userId(orderEntity.getUserId())
                .paymentMethod(orderEntity.getPaymentMethod())
                .deliveryMethod(orderEntity.getDeliveryMethod())
                .orderStatus(orderEntity.getStatus())
                .createDate(orderEntity.getCreateDate())
                .build();
    }

    public PageableOrderResponse<OrderResponse> buildPageableOrderResponse(Page<OrderEntity> orderEntityPage){
        return PageableOrderResponse.<OrderResponse>builder()
                .orders(orderEntityPage.map(this::buildOrderResponse).toList())
                .hasNextPage(orderEntityPage.hasNext())
                .lastPageNumber(orderEntityPage.getNumberOfElements())
                .totalPage(orderEntityPage.getTotalPages()).build();
    }

    public ProductIdsDto buildProductIdsDto(Long productId){
        return ProductIdsDto.builder().build();
    }
}
