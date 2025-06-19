package com.habitHatch.UserMgmt;

import com.google.i18n.phonenumbers.NumberParseException;
import com.habitHatch.Exception.InvalidValueException;
import com.habitHatch.Exception.MandatoryParameterException;
import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserLoginRequest;
import com.habitHatch.UserMgmt.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMgmtController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserMgmtService userService;
    @PostMapping(value = "/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) throws Exception {
        return userService.createUser(userRequest);
    }

    @PostMapping("v1/user/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getUserId(), userLoginRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return userService.generateJwtToken(userLoginRequest.getUserId());
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return null;
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
