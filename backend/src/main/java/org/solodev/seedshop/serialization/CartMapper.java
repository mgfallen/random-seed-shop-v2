package org.solodev.seedshop.serialization;

import org.modelmapper.ModelMapper;
import org.solodev.seedshop.dto.CartDTO;
import org.solodev.seedshop.model.Cart;
import org.solodev.seedshop.model.CustomUser;
import org.solodev.seedshop.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CartMapper(ModelMapper modelMapper, CustomUserService userService) {
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(CartDTO.class, Cart.class)
                .addMappings(mapping -> mapping.skip(Cart::setUser));

        modelMapper.typeMap(CartDTO.class, Cart.class).setPreConverter(context -> {
            CartDTO source = context.getSource();
            CustomUser customUser = userService.getUserById(source.getUserId());
            context.getDestination().setUser(customUser);
            return context.getDestination();
        });
    }

    public CartDTO mapEntityToDto(Cart entity) {
        return modelMapper.map(entity, CartDTO.class);
    }

    public Cart mapDtoToEntity(CartDTO dto) {
        return modelMapper.map(dto, Cart.class);
    }
}

