package org.solodev.seedshop.serialization;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.solodev.seedshop.dto.ProductDTO;
import org.solodev.seedshop.model.Category;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    public ProductMapper(ModelMapper modelMapper, CategoryRepository categoryRepository1) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository1;
    }


    public ProductDTO mapEntityToDto(Product entity) {
        ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
        if (entity.getCategory() != null) {
            dto.setCategoryId(entity.getCategory().getId());
        }
        return dto;
    }

    public Product mapDtoToEntity(ProductDTO dto) {
        Product entity = modelMapper.map(dto, Product.class);
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + dto.getCategoryId()));
            entity.setCategory(category);
        }
        return entity;
    }

}
