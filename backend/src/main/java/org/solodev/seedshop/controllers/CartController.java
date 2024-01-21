package org.solodev.seedshop.controllers;

import org.solodev.seedshop.dto.CartItemDTO;
import org.solodev.seedshop.model.Cart;
import org.solodev.seedshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

//    final CartService cartService;
//
//    @Autowired
//    public CartController(CartService cartService) {
//        this.cartService = cartService;
//    }
//
//    @PostMapping("/{cartId}/add")
//    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @RequestBody CartItemDTO cartItemDTO) {
//        Cart cart = cartService.addToCart(cartId, cartItemDTO);
//        return new ResponseEntity<>(cart, HttpStatus.OK);
//    }

}
