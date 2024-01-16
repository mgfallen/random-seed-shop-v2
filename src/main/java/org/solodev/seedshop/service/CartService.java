package org.solodev.seedshop.service;

import org.solodev.seedshop.dto.CartItemDTO;
import org.solodev.seedshop.model.Cart;
import org.solodev.seedshop.model.CartItem;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    private final CartRepository cartRepository;

    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart addToCart(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());

        Product product = productService.getProductById(cartItemDTO.getProductId());
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemDTO.getQuantity());

            if (cart.getCartItems() == null) {
                cart.setCartItems(new ArrayList<>());
            }

            cart.getCartItems().add(cartItem);
            cartRepository.save(cart);
        }

        return cart;
    }
}
