package com.habitHatch.Kafka;

import com.habitHatch.WaterIntake.entity.WaterIntake;
import com.habitHatch.WaterIntake.entity.WaterIntakeResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerConfig {
    @KafkaListener(topics = "HabitHatchTopic", groupId = "habit-hatch-group")
    public ResponseEntity<WaterIntakeResp> consumeMessage(WaterIntake message){
        WaterIntakeResp response= WaterIntakeResp.builder().
                message("Message sent to kafka topic successfully").build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
