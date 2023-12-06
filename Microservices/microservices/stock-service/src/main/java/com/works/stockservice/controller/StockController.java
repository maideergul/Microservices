package com.works.stockservice.controller;

import com.works.stockservice.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public Boolean stockCheck(@RequestParam String productName, int orderQuantity) {
        return stockService.stockCheck(productName, orderQuantity);
    }
}
