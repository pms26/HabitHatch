package com.habitHatch.UserMgmt;

import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserRequest;
import com.habitHatch.UserMgmt.entity.UserResponse;
import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.internal.Logger;

@Service
public class UserMgmtService{
    Logger logger = Logger.getLogger(UserMgmtService.class.getName());

    @Autowired
    UsersDao usersDao;
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        //save in db
        logger.warn("Creating user with ID: " + userRequest);
        if (userRequest.getUserId() == null || userRequest.getUserId().isEmpty()) {
            return new ResponseEntity<>("User ID is required", HttpStatus.BAD_REQUEST);
        }
        logger.warn("Creating user with ID: " + userRequest);
        Users userEntity = new Users();
        userEntity.setUserId(userRequest.getUserId());
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setMobileNumber(userRequest.getMobileNumber());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setIsPremium(userRequest.getIsPremium());

        usersDao.save(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<UserDetailsResponse> getUserDetails(String userId, UserDetailsRequest userRequest) {

        Users userEntity = usersDao.findByUserId(userId);
        userEntity.setAge(userRequest.getAge());
        userEntity.setHeartRate(userRequest.getHeartRate());
        userEntity.setHeight(userRequest.getHeight());
        userEntity.setWeight(userRequest.getWeight());
        usersDao.save(userEntity);
        UserDetailsResponse userDetailsResponse=UserDetailsResponse.builder().age(userEntity.getAge())
                .heartRate(userEntity.getHeartRate())
                .height(userEntity.getHeight())
                .weight(userEntity.getWeight())
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .mobileNumber(userEntity.getMobileNumber()).build();

        return new ResponseEntity<>(userDetailsResponse,HttpStatus.OK);
    }
}
