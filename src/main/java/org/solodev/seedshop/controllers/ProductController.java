package org.solodev.seedshop.controllers;

import org.solodev.seedshop.dto.ProductDTO;
import org.solodev.seedshop.model.Product;
import org.solodev.seedshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/add")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
    }
}
