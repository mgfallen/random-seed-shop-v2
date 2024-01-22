package org.solodev.seedshop.serialization;

import org.modelmapper.ModelMapper;
import org.solodev.seedshop.dto.CartDTO;
import org.solodev.seedshop.dto.CartItemDTO;
import org.solodev.seedshop.model.Cart;
import org.solodev.seedshop.model.CartItem;
import org.solodev.seedshop.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CartItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(CartDTO.class, Cart.class)
                .addMappings(mapping -> mapping.skip(Cart::setUser));

        modelMapper.typeMap(CartDTO.class, Cart.class).setPreConverter(context -> {
            CartDTO source = context.getSource();
            CustomUser customUser = new CustomUser();
            customUser.setId(source.getUserId());
            context.getDestination().setUser(customUser);
            return context.getDestination();
        });
    }

    public CartItemDTO mapEntityToDto(CartItem entity) {
        return modelMapper.map(entity, CartItemDTO.class);
    }

    public CartItem mapDtoToEntity(CartItemDTO dto) {
        return modelMapper.map(dto, CartItem.class);
    }
}

