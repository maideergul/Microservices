package com.works.emailservice.kafka;

import com.works.emailservice.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class OrderConsumer {
    private final EmailService emailService;

    public OrderConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(HashMap<String, String> orderEvent) {

        emailService.sendMail(orderEvent.get("email"), "Order Details",
                "ORDER " + orderEvent.get("orderStatus") + "\n\n"
                        + " Product Name : " + orderEvent.get("productName") + " \n "
                        + "Quantity : " + orderEvent.get("orderQuantity") + " \n "
                        + "Price : " + orderEvent.get("price") + " \n\n\n\n "
                        + "Thank you for choosing us!");
    }
}
