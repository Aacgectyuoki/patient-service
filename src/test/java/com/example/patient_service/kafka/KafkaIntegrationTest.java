package com.example.patient_service.kafka;

import com.example.patient_service.service.kafka.KafkaConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        topics = {"patient.requests"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093"}
)

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class KafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaConsumerService kafkaConsumer;

    @Test
    public void testKafkaIntegration() throws InterruptedException {
        String message = "testing123";

        // Send a message to the Kafka topic
        kafkaTemplate.send("patient.requests", message);

        // Wait for the consumer to receive the message
        Thread.sleep(2000);

        // Verify that the message was received
        assertThat(kafkaConsumer.getLastMessage()).isEqualTo(message);
    }
}