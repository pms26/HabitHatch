package com.habitHatch.UserMgmt.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class UserRequest {
    private String userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private Boolean isPremium;
}
