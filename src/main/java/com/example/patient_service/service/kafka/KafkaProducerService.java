package com.example.patient_service.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "patient.requests";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
//    private static final String TOPIC = "patient_topic"; // Replace with your topic name
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendMessage(String message) {
//        System.out.println("Producing message: " + message);
//        this.kafkaTemplate.send(TOPIC, message);
//    }
}
