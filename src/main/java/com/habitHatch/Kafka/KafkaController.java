package com.habitHatch.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    ProducerConfig producerConfig;

    @GetMapping("/v1/kafka")
    public void getKafkaStatus() {
       producerConfig.produceMessage("topic","Hello world");
    }

}
