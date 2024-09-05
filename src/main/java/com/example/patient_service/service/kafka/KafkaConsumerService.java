package com.example.patient_service.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private String lastMessage = "";

    @KafkaListener(topics = "patient.requests", groupId = "test-consumer-group")
    public void consume(String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
//    @KafkaListener(topics = "patient_topic", groupId = "my-group") // Replace with your topic name and group ID
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }
}
