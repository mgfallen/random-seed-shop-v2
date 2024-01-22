package org.solodev.seedshop.controllers;

import org.solodev.seedshop.dto.CustomOrderDTO;
import org.solodev.seedshop.dto.OrderItemDTO;
import org.solodev.seedshop.model.CustomOrder;
import org.solodev.seedshop.model.OrderItem;
import org.solodev.seedshop.serialization.CustomOrderMapper;
import org.solodev.seedshop.service.CustomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CustomOrderService orderService;

    private final CustomOrderMapper customOrderMapper;

    public OrderController(CustomOrderService orderService, CustomOrderMapper customOrderMapper) {
        this.orderService = orderService;
        this.customOrderMapper = customOrderMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CustomOrderDTO order) {
        CustomOrder customOrder = customOrderMapper.mapDtoToEntity(order);
        orderService.createOrder(customOrder);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CustomOrderDTO> getOrderById(@PathVariable Long orderId) {
        CustomOrderDTO order = orderService.getOrderById(orderId);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(@PathVariable Long orderId) {
        List<OrderItemDTO> orderItems = orderService.getOrderItems(orderId);
        return ResponseEntity.ok(orderItems);
    }

    @PostMapping("/{orderId}/addItem")
    public ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        orderService.addOrderItem(orderId, orderItem);
        return ResponseEntity.ok().build();
    }
}



