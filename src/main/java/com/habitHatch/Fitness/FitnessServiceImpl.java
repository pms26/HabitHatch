package com.habitHatch.Fitness;

import com.habitHatch.Exception.InvalidValueException;
import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FitnessServiceImpl {
    @Autowired
    UsersDao usersDao;

    public ResponseEntity<Double> calculateDailyWaterIntake(String userId) throws InvalidValueException {
        // Parse weight and age to numeric values

        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }
        double weightInKg = Double.parseDouble(String.valueOf(userEntity.getWeight()));
        int ageInYears = userEntity.getAge();

        // Base water intake calculation (35 ml per kg of weight)
        double waterIntakeInMl = weightInKg * 35;

        // Adjust for age
        if (ageInYears > 55) {
            waterIntakeInMl *= 0.9; // Reduce by 10% for older adults
        } else if (ageInYears < 18) {
            waterIntakeInMl *= 1.1; // Increase by 10% for younger individuals
        }

        // Adjust for gender
        if ("male".equalsIgnoreCase(userEntity.getGender())) {
            waterIntakeInMl *= 1.1; // Increase by 10% for males
        } else if ("female".equalsIgnoreCase(userEntity.getGender())) {
            waterIntakeInMl *= 1.0; // No change for females
        }

        // Convert milliliters to liters for the final result
        return new ResponseEntity<>(waterIntakeInMl / 1000, HttpStatus.OK);
    }
}
