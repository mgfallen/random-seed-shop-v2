package org.solodev.seedshop.service;

import org.solodev.seedshop.dto.*;
import org.solodev.seedshop.model.CustomOrder;
import org.solodev.seedshop.repository.CustomOrderRepository;
import org.solodev.seedshop.serialization.CustomOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

//@Service
public class CustomOrderService {

    private final CustomOrderRepository orderRepository;
    private final CustomOrderMapper orderMapper;

    private final CartService cartService;

    @Autowired
    public CustomOrderService(CustomOrderRepository orderRepository, CustomOrderMapper orderMapper, CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.cartService = cartService;
    }

    public List<CustomOrderDTO> getAllOrders() {
        List<CustomOrder> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public CustomOrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::mapEntityToDto)
                .orElse(null);
    }

    public void createOrder(CustomOrderDTO orderDTO) {
        CustomOrder order = orderMapper.mapDtoToEntity(orderDTO);
        // Дополнительная логика по обработке заказа, сохранению и т.д.
        orderRepository.save(order);
    }

//    public void createOrderOnCart(Long cartId) {
//        CartDTO cartDTO = cartService.getCart(cartId);
//        if (cartDTO != null && cartDTO.getUserId() != null) {
//            CustomOrderDTO orderDTO = new CustomOrderDTO();
//            orderDTO.setUserId(cartDTO.getUserId());
//            // Дополнительные поля заказа, если необходимо
//
//            createOrder(orderDTO);
//
//            // Дополнительная логика по очистке корзины или обновлению статуса заказа в корзине
//            // Например:
//            // cartService.clearCart(cartId);
//        }
//    }
}
