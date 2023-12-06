package com.works.productservice.controller;

import com.works.productservice.entity.Product;
import com.works.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> allProducts() {
        return productService.allProducts();
    }

    @PostMapping("/addProduct")
    public Map<String, Object> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Map<String, Object> findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PatchMapping("/updateProduct")
    public Map<String, Object> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
}
