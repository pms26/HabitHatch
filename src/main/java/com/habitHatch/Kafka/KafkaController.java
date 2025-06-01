package com.habitHatch.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    public ProducerConfig producerConfig;

    @GetMapping("/v1/kafka")
    public ResponseEntity<String> getKafkaStatus(@RequestParam("message") String message) {
       producerConfig.produceMessage("HabitHatchTopic",message);
       return new ResponseEntity<>("Message sent to Kafka topic successfully", HttpStatus.OK);
    }

}
