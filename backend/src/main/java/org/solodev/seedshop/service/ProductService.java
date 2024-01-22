package org.solodev.seedshop.service;

import org.solodev.seedshop.dto.ProductDTO;
import org.solodev.seedshop.serialization.ProductMapper;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapEntityToDto).toList();
    }

    public ProductDTO getProductById(Long productId) {
        return productMapper.mapEntityToDto(productRepository.findById(productId).orElse(null));
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.mapDtoToEntity(productDTO);
        productRepository.save(product);
    }

    @SuppressWarnings({})
    public void updateProduct(Product product) throws Exception {
        Long productId = product.getId();

        if (productId == null) {
            return;
        }

        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // Update fields you want to allow modification
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setRating(product.getRating());
            existingProduct.setCategory(product.getCategory());

            // Save the updated product
            productRepository.save(existingProduct);
        } else {
           throw new Exception("Product doesn't exist!");
        }
    }

}
