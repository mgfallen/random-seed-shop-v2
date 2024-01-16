package org.solodev.seedshop.dto;

import lombok.Data;
import org.solodev.seedshop.model.Category;

@Data
public class ProductDTO {
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Long categoryId;

    private Integer stockQuantity;
    private Double rating;
}
