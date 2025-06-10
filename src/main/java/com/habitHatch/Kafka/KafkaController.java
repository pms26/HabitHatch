package com.habitHatch.Kafka;

import com.habitHatch.WaterIntake.entityClass.WaterIntake;
import com.habitHatch.WaterIntake.entityClass.WaterIntakeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    @Autowired
    public ProducerConfig producerConfig;

    @PostMapping("/v1/kafka")
    public ResponseEntity<WaterIntakeResp> getKafkaStatus(@RequestBody WaterIntake message) {
       producerConfig.produceMessage("HabitHatchTopic_Json",message);
        WaterIntakeResp response= WaterIntakeResp.builder().
                message("Message sent to kafka topic successfully").build();
        return new ResponseEntity<>(response, HttpStatus.OK);    }

}
//controller --->ProducerConfig--->in kafka topic--->consumer config

