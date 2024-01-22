package org.solodev.seedshop.service;

import org.solodev.seedshop.dto.CartDTO;
import org.solodev.seedshop.dto.CartItemDTO;
import org.solodev.seedshop.model.Cart;
import org.solodev.seedshop.model.CartItem;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.CartRepository;
import org.solodev.seedshop.serialization.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.productService = productService;
    }

//    public CartDTO getCart(Long cartId) {
//        Cart cart = cartRepository.findById(cartId).orElse(null);
//        return (cart != null) ? cartMapper.mapEntityToDto(cart) : null;
//    }

//    public List<CartItemDTO> getCartItems(Long cartId) {
//        Cart cart = cartRepository.findById(cartId).orElse(null);
//        return (cart != null) ? cartMapper.mapEntityToDto(cart.getCartItems()) : null;
//    }

    public void addItemToCart(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            CartItem cartItem = cartMapper.mapDtoToEntity(cartItemDTO);
            cart.getCartItems().add(cartItem);
            cart.setTotalItems(cart.getTotalItems() + cartItem.getQuantity());
            cartRepository.save(cart);
        }
    }

    public void removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));
            cart.setTotalItems(cart.getCartItems().size());
            cartRepository.save(cart);
        }
    }

}
