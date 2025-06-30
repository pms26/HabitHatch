package com.habitHatch.UserMgmt;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.habitHatch.Exception.MandatoryParameterException;
import com.habitHatch.Exception.InvalidValueException;
import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserRequest;

import com.habitHatch.db.Users;
import com.habitHatch.db.UsersDao;
import com.habitHatch.security.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.internal.Logger;

import java.util.Optional;

@Service
public class UserMgmtService{
    Logger logger = Logger.getLogger(UserMgmtService.class.getName());

    @Autowired
    UsersDao usersDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    public ResponseEntity<?> createUser(UserRequest userRequest) throws Exception {

        try{
        logger.warn("Creating user with ID: " + userRequest);
        validateUserRequest(userRequest);

        Users userEntity = new Users();
        userEntity.setUserId(userRequest.getUserId());
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setMobileNumber(userRequest.getMobileNumber());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        if("ADMIN".equals(userRequest.getRole()))
            userEntity.setRole("ROLE_ADMIN");
        else
            userEntity.setRole("ROLE_USER");
        userEntity.setIsPremium(userRequest.getIsPremium());

        usersDao.save(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);

        } catch(MandatoryParameterException | InvalidValueException e){
            logger.warn("Error creating user: " + e.getMessage());
            throw e;
        } catch(Exception e){
            logger.warn("Error creating user: " + e.getMessage());
            throw new Exception("Error in creating user");
        }
    }


    public ResponseEntity<UserDetailsResponse> addUserDetails(String userId, UserDetailsRequest userRequest) throws InvalidValueException {

        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
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
    public ResponseEntity<?> updateUser(String userId, UserRequest userRequest) throws InvalidValueException, NumberParseException, MandatoryParameterException {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }
        validateUserRequest(userRequest);
        Optional.ofNullable(userRequest.getName()).ifPresent(userEntity::setName);
        Optional.ofNullable(userRequest.getMobileNumber()).ifPresent(userEntity::setMobileNumber);
        Optional.ofNullable(userRequest.getPassword()).ifPresent(userEntity::setPassword);

        usersDao.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<UserDetailsResponse> getUser(String userId) throws InvalidValueException {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
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
    public ResponseEntity<?> deleteUser(String userId) throws InvalidValueException {
        Users userEntity = usersDao.findByUserId(userId);
        if (userEntity == null) {
            throw new InvalidValueException("HH_User_106", "User not found with ID :" + userId);
        }
        usersDao.delete(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateUserRequest(UserRequest userRequest) throws MandatoryParameterException, InvalidValueException, NumberParseException {
        checkMandatoryParameters(userRequest);
        checkInvalidValue(userRequest);

    }
    private void checkMandatoryParameters(UserRequest userRequest) throws MandatoryParameterException {
        if (userRequest.getUserId() == null || userRequest.getUserId().isEmpty() || userRequest.getUserId().equalsIgnoreCase(" ")) {
            throw new MandatoryParameterException("HH_User_102", "Mandatory parameter missing: User ID");
        }
        if(userRequest.getName() == null || userRequest.getName().isEmpty() || userRequest.getName().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("HH_User_102", "Mandatory parameter missing: Name");
        }
        if(userRequest.getEmail() == null || userRequest.getEmail().isEmpty() || userRequest.getEmail().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("HH_User_102", "Mandatory parameter missing: Email");
        }
        if(userRequest.getMobileNumber() == null){
            throw new MandatoryParameterException("HH_User_102", "Mandatory parameter missing: Mobile Number");
        }
        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty() || userRequest.getPassword().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("HH_User_102", "Mandatory parameter missing: Password");
        }
        if(userRequest.getCountryCode() == null || userRequest.getCountryCode().isEmpty() || userRequest.getCountryCode().equalsIgnoreCase(" ")){
            throw new MandatoryParameterException("HH_User_102", "Mandatory parameter missing: Country Code");
        }
        if(userRequest.getGender() == null || userRequest.getGender().isEmpty() ) {
            throw new MandatoryParameterException("HH_User_102","Mandatory parameter missing: Gender");
        }
    }
    private void checkInvalidValue(UserRequest userRequest) throws InvalidValueException, NumberParseException {
        // Check if user already exists
        Users userExists = usersDao.findByUserId(userRequest.getUserId());
        if(null != userExists) {
            throw new InvalidValueException("HH_User_103", "User already exists with ID: " + userRequest.getUserId());
        }
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(userRequest.getMobileNumber().toString(), userRequest.getCountryCode());
        if (!phoneNumberUtil.isValidNumber(parsedNumber)) {
            throw new InvalidValueException("HH_User_104", "Invalid phone number: " + userRequest.getMobileNumber());
        }
        //Check the name should be 3 letter atleast
        if(userRequest.getName().length()<3 ){
            throw new InvalidValueException("HH_User_103","Name should greater than 3 characters");
        }
        //password validation with regex
        if(!userRequest.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).+$")) {
            throw new InvalidValueException("HH_User_104", "Password should contain at least 8 characters i.e., one uppercase letter, one lowercase letter, and one special character");
        }
        // Check Email validation
        if(!userRequest.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidValueException("HH_User_105", "Email is not valid");
        }
    }

    public ResponseEntity<?> userLogin(UserLogin userLogin) {
        Users userEntity= usersDao.findByUserId(userLogin.getUserId());
        if (userEntity == null) {
            return new ResponseEntity<>("User not found with ID: " + userLogin.getUserId(), HttpStatus.NOT_FOUND);
        }
        if (passwordEncoder.matches(userLogin.getPassword(), userEntity.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }
    }
}
