package com.works.productservice.service;

import com.works.productservice.entity.Product;
import com.works.productservice.repository.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<String, Object> addProduct(Product product) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            productRepository.save(product);
            map.put("Status", true);
            map.put("Result", product);
        } catch (Exception e) {
            map.put("Status", false);
            map.put("Result", product);
        }

        return map;
    }

    public Map<String, Object> deleteProduct(int id) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            productRepository.deleteById(id);
            map.put("Status", true);
            map.put("Result", id);
        } catch (Exception e) {
            map.put("Status", false);
            map.put("Result", id);
        }

        return map;
    }

    public Map<String, Object> findProductById(int id) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Product product = productRepository.findById(id).orElseThrow();
            map.put("Status", true);
            map.put("Result", product);
        } catch (Exception e) {
            map.put("Status", false);
            map.put("Result", id);
        }
        return map;
    }

    public Map<String, Object> updateProduct(Product product) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Product existingProduct = productRepository.findByProductId(product.getProductId());
            if (existingProduct != null) {
                productRepository.saveAndFlush(product);
                map.put("Status", true);
                map.put("Result", product);
            }
        } catch (Exception e) {
            map.put("Status", false);
        }
        return map;
    }

    @Cacheable(value = "productList")
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

}
