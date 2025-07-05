package com.habitHatch.WaterIntake;

import com.habitHatch.Exception.InvalidValueException;
//import com.habitHatch.Kafka.ProducerConfig;
import com.habitHatch.WaterIntake.entityClass.WaterIntake;
import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalTime;


@Service
@Slf4j
public class WaterIntakeService {
    @Autowired
    UsersDao usersDao;
    public ResponseEntity<?> getWaterConsumed(String userId, Double waterConsumed) throws InvalidValueException {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }
        userEntity.setWaterConsumed(waterConsumed);
        usersDao.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
