package com.works.orderservice.service;

import com.works.orderservice.entity.Order;
import com.works.orderservice.kafka.OrderProducer;
import com.works.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public Order newOrder(Order order) {

        if (checkStock(order.getProductName(), order.getOrderQuantity())) {
            order.setOrderStatus("APPROVED");

            HashMap<String, String> orderEvent = new LinkedHashMap<>();
            orderEvent.put("productName", order.getProductName());
            orderEvent.put("orderQuantity", "" + order.getOrderQuantity());
            orderEvent.put("price", "" + order.getPrice());
            orderEvent.put("email", order.getEmail());
            orderEvent.put("orderStatus", order.getOrderStatus());

            orderRepository.save(order);
            orderProducer.sendMessage(orderEvent);

        } else {
            order.setOrderStatus("OUT OF STOCK");
            orderRepository.save(order);
        }
        return order;
    }

    public Boolean checkStock(String productName, int orderQuantity) {
        String url = "http://localhost:8082/api/v1/stock?productName={productName}&orderQuantity={orderQuantity}";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Boolean.class, productName, orderQuantity);
    }
}
