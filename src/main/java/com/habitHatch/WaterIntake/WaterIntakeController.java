package com.habitHatch.WaterIntake;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WaterIntakeController {

    @PostMapping("/v1/water-intake/reminder")
    public ResponseEntity<?> calculateWaterReminder(String userId, Double waterConsumed) {
        WaterIntakeService waterIntakeService = new WaterIntakeService();
        return waterIntakeService.WaterReminderCalculator(userId, waterConsumed);
    }
}
