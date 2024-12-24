package az.ingress.mapper;

import az.ingress.dao.entity.AddressEntity;
import az.ingress.model.request.AddressInfo;

public enum AddressMapper {
    ADDRESS_MAPPER;

    public AddressEntity buildAddressEntity(AddressInfo addressInfo){
        return AddressEntity.builder()
                .city(addressInfo.getCity())
                .street(addressInfo.getStreet())
                .content(addressInfo.getContent())
                .build();
    }
}
