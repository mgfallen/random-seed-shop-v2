package org.solodev.seedshop.serialization;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.solodev.seedshop.dto.CustomOrderDTO;
import org.solodev.seedshop.dto.OrderItemDTO;
import org.solodev.seedshop.model.CustomOrder;
import org.solodev.seedshop.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class CustomOrderMapper {

    private final ModelMapper modelMapper;

    public CustomOrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<CustomOrder, CustomOrderDTO> orderMap = modelMapper.createTypeMap(CustomOrder.class, CustomOrderDTO.class);
        orderMap.addMapping(CustomOrder::getOrderItems, CustomOrderDTO::setOrderItems);
    }

    public CustomOrderDTO mapEntityToDto(CustomOrder customOrder) {
        return modelMapper.map(customOrder, CustomOrderDTO.class);
    }

    public CustomOrder mapDtoToEntity(CustomOrderDTO customOrderDTO) {
        return modelMapper.map(customOrderDTO, CustomOrder.class);
    }

    public OrderItemDTO mapEntityToDto(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemDTO.class);
    }

    public OrderItem mapDtoToEntity(OrderItemDTO orderItemDTO) {
        return modelMapper.map(orderItemDTO, OrderItem.class);
    }
}
