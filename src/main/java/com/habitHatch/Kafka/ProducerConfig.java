package com.habitHatch.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerConfig {

    private KafkaTemplate<String,String> kafkaTemplate;

    public ProducerConfig(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void produceMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
