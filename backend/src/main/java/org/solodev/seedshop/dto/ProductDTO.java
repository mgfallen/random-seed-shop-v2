package org.solodev.seedshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.solodev.seedshop.model.Category;

@Data
public class ProductDTO {
    @NotEmpty(message = "id could not be empty")
    private Long id;

    @NotEmpty(message = "name could not be empty")
    private String name;
    private String description;
    @Min(value = 0, message = "price could be only positive number")
    private Double price;
    private Long categoryId;

    @Min(value = 0, message = "stock quantity could be only non-negative number")
    private Integer stockQuantity;

    @Min(value = 0, message = "rating could be only non-negative number")
    private Double rating;
}
