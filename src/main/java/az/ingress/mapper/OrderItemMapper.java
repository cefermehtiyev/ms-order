package az.ingress.mapper;

import az.ingress.dao.entity.OrderEntity;
import az.ingress.dao.entity.OrderItemEntity;
import az.ingress.model.dto.ProductDto;
import az.ingress.model.request.OrderItemInfo;

public enum OrderItemMapper {
    ORDER_ITEM_MAPPER;



    public OrderItemEntity buildOrderItemEntity( OrderEntity orderEntity,ProductDto productDto){
        return OrderItemEntity.builder()
                .order(orderEntity)
                .productId(productDto.getProductId())
                .quantity(productDto.getQuantity())
                .productName(productDto.getName())
                .pricePerUnit(productDto.getPrice())
                .build();
    }

    public void setProductToOrderItem(OrderItemEntity orderItemEntity , ProductDto productDto){
        orderItemEntity.setProductId(productDto.getProductId());
        orderItemEntity.setProductName(productDto.getName());
        orderItemEntity.setQuantity(productDto.getQuantity());
        orderItemEntity.setPricePerUnit(productDto.getPrice());
    }
}
