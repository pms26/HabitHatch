package com.habitHatch.Kafka;

import com.habitHatch.WaterIntake.entityClass.WaterIntake;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerConfig {

    @KafkaListener(topics = "HabitHatchTopic_Json", groupId = "habit-hatch-group")
    public void consumeMessage(WaterIntake message){
        EmailService emailService = new EmailService();
        emailService.sendEmail(message.getUserEmail(),"Pani pilo dost +"+message.getUserId(),"You have consumed "
                + message.getWaterReminder() + " L of water today. Keep it up! Remember to drink more water to stay hydrated.");

    }


}
