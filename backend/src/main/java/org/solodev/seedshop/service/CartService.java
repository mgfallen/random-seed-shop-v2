package org.solodev.seedshop.service;

import jakarta.transaction.Transactional;
import org.solodev.seedshop.dto.CartDTO;
import org.solodev.seedshop.dto.CartItemDTO;
import org.solodev.seedshop.model.*;
import org.solodev.seedshop.repository.CartRepository;
import org.solodev.seedshop.serialization.CartItemMapper;
import org.solodev.seedshop.serialization.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final ProductService productService;
    private final CustomOrderService orderService;

    @Autowired
    public CartService(CartRepository cartRepository, CartMapper cartMapper, CartItemMapper cartItemMapper, ProductService productService, CustomOrderService orderService) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.productService = productService;
        this.orderService = orderService;
    }

    public CartDTO getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        return (cart != null) ? cartMapper.mapEntityToDto(cart) : null;
    }

    public void addItemToCart(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            CartItem cartItem = cartItemMapper.mapDtoToEntity(cartItemDTO);
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

    public void clearCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public CartDTO viewCart(CustomUser user) {
        Optional<Cart> cart = cartRepository.findById(user.getId());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.get().getId());
        cartDTO.setTotalItems(cart.get().getTotalItems());

        return cartDTO;
    }


    @Transactional
    public void checkoutCart(CustomUser user) {
        Optional<Cart> cart = cartRepository.findById(user.getId());

        // Perform checkout logic (e.g., create an order and update inventory)
        CustomOrder order = cartRepository.createOrderFromCart(cart);
        updateInventory(cart);

        // Clear the cart or mark it as checked out, depending on your business logic
        cart.get().setCheckedOut(true);
        cartRepository.markCartAsCheckedOut(cart.get().getId());
    }

    private CustomOrder createOrderFromCart(Cart cart) {
        CustomOrder order = new CustomOrder();
        order.setUser(cart.getUser());
        order.setOrderItems(cart.getCartItems().stream()
                .map(this::createOrderItemFromCartItem)
                .collect(Collectors.toList()));
        // Set other order details as needed
        orderService.createOrder(order); // Assuming you have a method to save the order
        return order;
    }

    private OrderItem createOrderItemFromCartItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        return orderItem;
    }

    private void updateInventory(Optional<Cart> cart) {
        for (CartItem cartItem : cart.get().getCartItems()) {
            Product product = cartItem.getProduct();
            int newStockQuantity = product.getStockQuantity() - cartItem.getQuantity();
            product.setStockQuantity(newStockQuantity);
            try {
                productService.updateProduct(product); // Assuming you have a method to update the product
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
