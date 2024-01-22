package org.solodev.seedshop.controllers;

import jakarta.validation.Valid;
import org.solodev.seedshop.dto.ProductDTO;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);

        return ResponseEntity
                .created(URI.create("/api/products/" + productDTO.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }


}
