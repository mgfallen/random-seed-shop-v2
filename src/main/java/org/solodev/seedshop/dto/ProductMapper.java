package org.solodev.seedshop.dto;

import org.modelmapper.ModelMapper;
import org.solodev.seedshop.model.Category;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Product mapDtoToEntity(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
            product.setCategory(category);
        }

        return product;
    }

}
