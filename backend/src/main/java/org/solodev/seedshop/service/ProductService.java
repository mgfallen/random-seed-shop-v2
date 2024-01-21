package org.solodev.seedshop.service;

import org.solodev.seedshop.dto.ProductDTO;
import org.solodev.seedshop.serialization.ProductMapper;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.mapDtoToEntity(productDTO);
        productRepository.save(product);
    }
}
