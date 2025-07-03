//package com.habitHatch.WaterIntake;
//
//import com.habitHatch.Kafka.ProducerConfig;
//import com.habitHatch.WaterIntake.entityClass.WaterIntake;
//import com.habitHatch.db.Users;
//import com.habitHatch.db.UsersDao;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalTime;
//@Service
//@Slf4j
//public class NotificationHandler {
//    @Autowired
//    public ProducerConfig producerConfig;
//    @Autowired
//    UsersDao usersDao;
//
//
//    private void notificationToKafka(WaterIntake waterIntake) {
//        int localTime = LocalTime.now().getHour();
//        int notifications = 0;
//
//        System.out.println("Current Time: " + localTime);
//        if(localTime >= 6 && localTime< 22 && notifications <= 5){
//
//            producerConfig.produceMessage("HabitHatchTopic_Json", waterIntake);
//            log.info("Notification count: " + notifications);
//        if (localTime == 0) {
//            notifications = 0;
//            log.info("Notification count reset to 0 at midnight"+notifications);
//        }
//    }}
//}
