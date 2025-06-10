package com.habitHatch.WaterIntake;

import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WaterIntakeService {
    @Autowired
    UsersDao usersDao;


    Double WaterReminderCalculator(String userId, Double waterConsumed) {
        Users userEntity = usersDao.findByUserId(userId);
        Double totalWaterIntake = userEntity.getWaterIntake();
        double waterToDrink, waterReminder;
        waterToDrink = totalWaterIntake - waterConsumed;
        waterReminder = waterToDrink / 5; // Assuming 5 reminders in a day
        return waterReminder;
    }
}
