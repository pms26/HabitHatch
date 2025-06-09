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


        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }
        double weightInKg = Double.parseDouble(String.valueOf(userEntity.getWeight()));
        int ageInYears = userEntity.getAge();

        double waterIntakeInMl = weightInKg * 35;

        if (ageInYears > 55) {
            waterIntakeInMl *= 0.9;
        } else if (ageInYears < 18) {
            waterIntakeInMl *= 1.1;
        }

        if ("male".equalsIgnoreCase(userEntity.getGender())) {
            waterIntakeInMl *= 1.1;
        } else if ("female".equalsIgnoreCase(userEntity.getGender())) {
            waterIntakeInMl *= 1.0;
        }
       userEntity.setWaterIntake(waterIntakeInMl/1000);
        usersDao.save(userEntity);

        return new ResponseEntity<>(waterIntakeInMl / 1000, HttpStatus.OK);
    }

    public ResponseEntity<Double> getBMI(String userId) throws InvalidValueException {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }

        double bmi = userEntity.getWeight() / ((userEntity.getHeight() / 100.0) * (userEntity.getHeight() / 100.0));
        userEntity.setBmi(bmi);
        return new ResponseEntity<>(Math.ceil(bmi * 100) / 100.0,HttpStatus.OK);
    }
}
