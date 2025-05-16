package com.habitHatch.Fitness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FitnessController {
    @Autowired
    private FitnessServiceImpl fitnessService;

    @GetMapping("/v1/waterIntake/{userId}")
    public ResponseEntity<Double> calculateWaterIntake(@PathVariable String userId) throws Exception {
        return fitnessService.calculateDailyWaterIntake(userId);
    }

    @GetMapping("/v1/getBMI/{userId}")
    public ResponseEntity<Double> getBMI(@PathVariable String userId) throws Exception{
        return fitnessService.getBMI(userId);
    }

}
