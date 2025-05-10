package com.habitHatch.UserMgmt.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class UserDetailsRequest {
    private String weight;
    private String height;
    private String age;
    private String heartRate;
}
