package org.solodev.seedshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomOrderDTO {
    private Long id;
    private Long userId;
    private List<OrderItemDTO> orderItems;
    private String shippingAddress;
    private String orderStatus;
}
