package com.habitHatch.Kafka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @GetMapping("/v1/kafka")
    public String getKafkaStatus() {
        return "Kafka is running";
    }

}
