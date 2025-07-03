//package com.habitHatch.Kafka;
//
//import com.habitHatch.WaterIntake.entityClass.WaterIntake;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class ConsumerConfig {
//
//
//    @KafkaListener(topics = "HabitHatchTopic_Json", groupId = "habit-hatch-group")
//    public void consumeMessage(WaterIntake message){
//        log.info("Consumed message: " + message);
//        MessagingService messagingService = new MessagingService();
//        messagingService.sendEmail(message.getUserEmail(),"Pani pilo dost +"+message.getUserId(),"You have consumed "
//                + message.getWaterReminder() + " L of water today. Keep it up! Remember to drink more water to stay hydrated.");
//    }
//
//
//}
