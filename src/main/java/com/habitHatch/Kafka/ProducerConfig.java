package com.habitHatch.Kafka;

import com.habitHatch.WaterIntake.entityClass.WaterIntake;
import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerConfig {

    private KafkaTemplate<String,WaterIntake> kafkaTemplate;
    @Autowired
    UsersDao usersDao;
    public ProducerConfig(KafkaTemplate<String, WaterIntake> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void produceMessage(String topic, WaterIntake message) {
        log.info("Producing message to topic: {}", topic);
        Double waterToDrink, waterReminder;
        for(Users user : usersDao.findAll()){
            log.info("Processing user: {}", user.getUserId());
            if (user.getWaterIntake() == null || user.getWaterConsumed() == null) {
                continue; // Skip users with null values
            }
            waterToDrink = user.getWaterIntake() - user.getWaterConsumed();
            waterReminder = waterToDrink / 5;
            WaterIntake waterIntake = WaterIntake.builder().
                    userId(user.getUserId())
                    .userEmail(user.getEmail()).
                    totalWaterIntake(user.getWaterIntake()).
                    waterConsumedinML(user.getWaterConsumed()).
                    waterToDrink(waterToDrink).waterReminder(waterReminder).build();
            log.info("Water intake for user {}: {}", user.getUserId(), waterIntake);
            Message<WaterIntake> kafkaMessage = org.springframework.messaging.support.MessageBuilder
                    .withPayload(waterIntake)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();
            log.info("Sending message to Kafka topic: {}", kafkaMessage);
            kafkaTemplate.send(topic, kafkaMessage.getPayload());
        }

    }
}
