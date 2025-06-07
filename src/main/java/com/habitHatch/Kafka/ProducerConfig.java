package com.habitHatch.Kafka;

import com.habitHatch.WaterIntake.entity.WaterIntake;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ProducerConfig {

    private KafkaTemplate<String,WaterIntake> kafkaTemplate;

    public ProducerConfig(KafkaTemplate<String, WaterIntake> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void produceMessage(String topic, WaterIntake message) {

        Message<WaterIntake> kafkaMessage = org.springframework.messaging.support.MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(topic, kafkaMessage.getPayload());
    }
}
