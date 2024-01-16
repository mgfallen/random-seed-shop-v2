package org.solodev.seedshop.service;

import jakarta.annotation.Nullable;
import org.solodev.seedshop.model.CustomOrder;
import org.solodev.seedshop.model.CustomUser;
import org.solodev.seedshop.repository.CustomOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOrderService {
    private final CustomOrderRepository customOrderRepository;

    @Autowired
    public CustomOrderService(CustomOrderRepository customOrderRepository) {
        this.customOrderRepository = customOrderRepository;
    }

//    public List<CustomOrder> getAllOrders() {
//        return customOrderRepository.findAll();
//    }
//
//    public @Nullable CustomOrder getOrderById(Long id) {
//        return customOrderRepository.findById(id).orElse(null);
//    }
//
//    public void saveOrder(CustomOrder order) {
//        customOrderRepository.save(order);
//    }
//
//    public void deleteOrder(Long id) {
//        customOrderRepository.deleteById(id);
//    }
//
//    public List<CustomOrder> getOrdersByUser(CustomUser user) {
//        return customOrderRepository.findByUser(user);
//    }
}
