package com.works.stockservice.repository;

import com.works.stockservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Boolean existsByProductNameAndStockQuantityGreaterThanEqual(String productName, int stockQuantity);

    Stock findByProductName(String productName);
}
