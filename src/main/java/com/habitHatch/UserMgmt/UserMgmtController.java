package com.habitHatch.UserMgmt;

import com.google.i18n.phonenumbers.NumberParseException;
import com.habitHatch.Exception.InvalidValueException;
import com.habitHatch.Exception.MandatoryParameterException;
import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserRequest;
import com.habitHatch.security.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMgmtController {

    @Autowired
    UserMgmtService userService;
    @PostMapping(value = "/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) throws Exception {
        return userService.createUser(userRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLogin userLogin) throws InvalidValueException {
        return userService.userLogin(userLogin);
    }

    @PostMapping("/v1/user/details/{userId}")
    public ResponseEntity<UserDetailsResponse> addUserDetails(@PathVariable String userId , @RequestBody UserDetailsRequest userRequest) throws InvalidValueException {
        return userService.addUserDetails(userId,userRequest);
    }
    @GetMapping("/v1/user/{userId}")
    public ResponseEntity<UserDetailsResponse> getUser(@PathVariable String userId) throws InvalidValueException {
        return userService.getUser(userId);
    }
    @PatchMapping("/v1/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) throws InvalidValueException, NumberParseException, MandatoryParameterException {
        return userService.updateUser(userId,userRequest);
    }
    @DeleteMapping("/v1/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) throws InvalidValueException {
        return userService.deleteUser(userId);
    }
//exception handling
    //optimization
}
