package com.works.stockservice.service;

import com.works.stockservice.entity.Stock;
import com.works.stockservice.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Boolean stockCheck(String productName, int orderQuantity) {
        Boolean isStockExist = stockRepository.existsByProductNameAndStockQuantityGreaterThanEqual(productName, orderQuantity);
        if (isStockExist) {
            Stock stockUpdate = stockRepository.findByProductName(productName);
            stockUpdate.setStockQuantity((stockUpdate.getStockQuantity() - orderQuantity));
            stockRepository.saveAndFlush(stockUpdate);
            return true;
        } else {
            return false;
        }
    }
}
