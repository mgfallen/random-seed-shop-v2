package org.solodev.seedshop.controllers;

import org.solodev.seedshop.dto.CustomOrderDTO;
import org.solodev.seedshop.model.CustomOrder;
import org.solodev.seedshop.model.OrderItem;
import org.solodev.seedshop.service.CustomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

//    @Autowired
//    private CustomOrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<Void> createOrder(@RequestBody CustomOrderDTO order) {
//        orderService.createOrder(order);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/{orderId}")
//    public ResponseEntity<CustomOrder> getOrderById(@PathVariable Long orderId) {
//        CustomOrder order = orderService.getOrderById(orderId);
//        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/{orderId}/items")
//    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
//        List<OrderItem> orderItems = orderService.getOrderItems(orderId);
//        return ResponseEntity.ok(orderItems);
//    }
//
//    @PostMapping("/{orderId}/addItem")
//    public ResponseEntity<Void> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
//        orderService.addOrderItem(orderId, orderItem);
//        return ResponseEntity.ok().build();
//    }
}

