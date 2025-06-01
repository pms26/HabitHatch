package com.habitHatch.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerConfig {
    @KafkaListener(topics = "HabitHatchTopic", groupId = "habit-hatch-group")
    public void consumeMessage(String message){
        System.out.println("Message in consumer from Kafka topic: " + message);
    }

}
