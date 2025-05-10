package com.habitHatch.UserMgmt;

import com.habitHatch.UserMgmt.entity.UserDetailsRequest;
import com.habitHatch.UserMgmt.entity.UserDetailsResponse;
import com.habitHatch.UserMgmt.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMgmtController {

    @Autowired
    UserMgmtService userService;
    @PostMapping(value = "/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/v1/user/details/{userId}")
    public ResponseEntity<UserDetailsResponse> addUserDetails(@PathVariable String userId , @RequestBody UserDetailsRequest userRequest) {
        return userService.addUserDetails(userId,userRequest);
    }
    @GetMapping("/v1/user/{userId}")
    public ResponseEntity<UserDetailsResponse> getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
    @PatchMapping("/v1/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest){
        return userService.updateUser(userId,userRequest);
    }

}
