package com.habitHatch.UserMgmt.entity;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userId;
    private String password;
}
