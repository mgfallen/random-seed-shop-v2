package org.solodev.seedshop.controllers;

import org.solodev.seedshop.dto.CartDTO;
import org.solodev.seedshop.dto.CartItemDTO;
import org.solodev.seedshop.model.CustomUser;
import org.solodev.seedshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartItemDTO> addToCart(@PathVariable Long cartId, @RequestBody CartItemDTO cartItemDTO) {
        cartService.addItemToCart(cartId, cartItemDTO);
        return new ResponseEntity<>(cartItemDTO, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<CartDTO> viewCart(CustomUser user) {
        CartDTO cartDTO = cartService.viewCart(user);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkoutCart(CustomUser user) {
        cartService.checkoutCart(user);
        return ResponseEntity.ok("Cart checked out successfully.");
    }

}
