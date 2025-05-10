package com.habitHatch.UserMgmt;

import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserRequest;
import com.habitHatch.UserMgmt.entity.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMgmtController {

    @Autowired
    UserMgmtService userService;
    @PostMapping(value = "/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/v1/user/details/{userId}")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable String userId ,@RequestBody UserDetailsRequest userRequest) {
        return userService.getUserDetails(userId,userRequest);
    }

}
