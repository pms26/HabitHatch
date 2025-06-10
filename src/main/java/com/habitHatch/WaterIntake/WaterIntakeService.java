package com.habitHatch.WaterIntake;

import com.habitHatch.Exception.InvalidValueException;
import com.habitHatch.Kafka.ProducerConfig;
import com.habitHatch.WaterIntake.entityClass.WaterIntake;
import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class WaterIntakeService {
    @Autowired
    UsersDao usersDao;
    @Autowired
    public ProducerConfig producerConfig;


    public ResponseEntity<?> WaterReminderCalculator(String userId, Double waterConsumed) throws InvalidValueException {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }
        Double totalWaterIntake = userEntity.getWaterIntake();
        double waterToDrink, waterReminder;
        waterToDrink = totalWaterIntake - waterConsumed;
        waterReminder = waterToDrink / 5;
        WaterIntake waterIntake = WaterIntake.builder().waterIntakeInMl(totalWaterIntake).waterConsumedinML(waterConsumed).waterToDrinkInMl(waterToDrink).waterReminder(waterReminder).build();
       producerConfig.produceMessage("HabitHatchTopic_Json", waterIntake);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
