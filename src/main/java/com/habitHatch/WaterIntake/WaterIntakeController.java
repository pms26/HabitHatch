package com.habitHatch.WaterIntake;

import com.habitHatch.Exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WaterIntakeController {
    @Autowired
    private WaterIntakeService waterIntakeService;

    @PostMapping("/v1/water-intake/reminder")
    public ResponseEntity<?> getWaterConsumed(@RequestParam  String userId, @RequestParam Double waterConsumed) throws InvalidValueException {
        return waterIntakeService.getWaterConsumed(userId, waterConsumed);
    }
}
