package com.habitHatch.Kafka;

import com.habitHatch.WaterIntake.entityClass.WaterIntake;
import com.habitHatch.WaterIntake.entityClass.WaterIntakeResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerConfig {
    @KafkaListener(topics = "HabitHatchTopic_Json", groupId = "habit-hatch-group")
    public ResponseEntity<String> consumeMessage(WaterIntake message){

        return new ResponseEntity<>("Message received from Kafka topic successfully", HttpStatus.OK);


    }

}
