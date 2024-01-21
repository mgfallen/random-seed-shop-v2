package org.solodev.seedshop.serialization;

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

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        TypeMap<ProductDTO, Product> typeMap = modelMapper.createTypeMap(ProductDTO.class, Product.class);
        typeMap.addMappings(mapping -> mapping.skip(Product::setCategory));

        typeMap.setPreConverter(context -> {
            ProductDTO source = context.getSource();
            if (source.getCategoryId() != null) {
                Category category = categoryRepository.findById(source.getCategoryId()).orElse(null);
                context.getDestination().setCategory(category);
            }
            return context.getDestination();
        });
    }

    public ProductDTO mapEntityToDto(Product entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    public Product mapDtoToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }
}
