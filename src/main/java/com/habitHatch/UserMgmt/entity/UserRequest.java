package com.habitHatch.UserMgmt.entity;

import org.springframework.data.annotation.Id;

public class UserRequest {
    private String userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private Boolean isPremium;

}
