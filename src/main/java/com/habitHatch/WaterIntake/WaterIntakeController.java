package com.habitHatch.WaterIntake;

import org.springframework.stereotype.Controller;

@Controller
public class WaterIntakeController {

    public Double calculateWaterReminder(String userId, Double waterConsumed) {
        WaterIntakeService waterIntakeService = new WaterIntakeService();
        return waterIntakeService.WaterReminderCalculator(userId, waterConsumed);
    }
}
