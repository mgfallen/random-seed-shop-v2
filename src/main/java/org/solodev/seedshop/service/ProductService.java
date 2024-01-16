package org.solodev.seedshop.service;

import jakarta.annotation.Nullable;
import org.solodev.seedshop.dto.ProductDTO;
import org.solodev.seedshop.dto.ProductMapper;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public @Nullable Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(ProductDTO productDTO) {
        Product product = productMapper.mapDtoToEntity(productDTO);
        productRepository.save(product);
    }
}
