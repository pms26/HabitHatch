package com.habitHatch.UserMgmt;

import com.habitHatch.UserMgmt.entity.UserRequest;
import com.habitHatch.UserMgmt.entity.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class UserMgmtController {
    @Autowired
    UserMgmtService userService;
    @PostMapping("/v1/user")
    public UserResponse createUser(UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

}
