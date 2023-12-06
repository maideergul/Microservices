package com.works.orderservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class OrderProducer {

    private final NewTopic topic;
    private final KafkaTemplate<String, HashMap<String, String>> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, HashMap<String, String>> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(HashMap<String, String> map) {

        Message<HashMap<String, String>> message = MessageBuilder
                .withPayload(map)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
