package com.habitHatch.UserMgmt;

import com.habitHatch.Exception.MandatoryParameterException;
import com.habitHatch.Exception.UserExistsException;
import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserRequest;

import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.internal.Logger;

import java.util.Optional;

@Service
public class UserMgmtService{
    Logger logger = Logger.getLogger(UserMgmtService.class.getName());

    @Autowired
    UsersDao usersDao;

    public ResponseEntity<?> createUser(UserRequest userRequest) throws Exception {
        //save in db Abd ABD abd
        try{
        logger.warn("Creating user with ID: " + userRequest);
        if (userRequest.getUserId() == null || userRequest.getUserId().isEmpty() || userRequest.getUserId().equalsIgnoreCase(" ")) {
            throw new MandatoryParameterException("102", "Mandatory parameter missing: User ID");
        }
        if(userRequest.getName() == null || userRequest.getName().isEmpty() || userRequest.getName().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("102", "Mandatory parameter missing: Name");
        }
        if(userRequest.getEmail() == null || userRequest.getEmail().isEmpty() || userRequest.getEmail().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("102", "Mandatory parameter missing: Email");
        }
        if(userRequest.getMobileNumber() == null){
            throw new MandatoryParameterException("102", "Mandatory parameter missing: Mobile Number");
        }
        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty() || userRequest.getPassword().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("102", "Mandatory parameter missing: Password");
        }

        // Check if user already exists
            Users userExists = usersDao.findByUserId(userRequest.getUserId());
        if(null != userExists) {
            throw new UserExistsException("103", "User already exists with ID: " + userRequest.getUserId());
        }
        Users userEntity = new Users();
        userEntity.setUserId(userRequest.getUserId());
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setMobileNumber(userRequest.getMobileNumber());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setIsPremium(userRequest.getIsPremium());

        usersDao.save(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);

        } catch(MandatoryParameterException | UserExistsException e){
            logger.warn("Error creating user: " + e.getMessage());
            throw e;
        } catch(Exception e){
            logger.warn("Error creating user: " + e.getMessage());
            throw new Exception("Error creating user: " + e.getMessage());
        }
    }

    public ResponseEntity<UserDetailsResponse> addUserDetails(String userId, UserDetailsRequest userRequest) {

        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<?> updateUser(String userId, UserRequest userRequest) {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Optional.ofNullable(userRequest.getName()).ifPresent(userEntity::setName);
        Optional.ofNullable(userRequest.getMobileNumber()).ifPresent(userEntity::setMobileNumber);
        Optional.ofNullable(userRequest.getPassword()).ifPresent(userEntity::setPassword);

        usersDao.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<UserDetailsResponse> getUser(String userId){
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        UserDetailsResponse getResponse= UserDetailsResponse.builder().age(userEntity.getAge())
                .heartRate(userEntity.getHeartRate())
                .height(userEntity.getHeight())
                .weight(userEntity.getWeight())
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .mobileNumber(userEntity.getMobileNumber()).build();
        return new ResponseEntity<> ( getResponse,HttpStatus.OK);
    }
    public ResponseEntity<?> deleteUser(String userId){
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        usersDao.delete(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
