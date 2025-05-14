package com.habitHatch.UserMgmt.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class UserDetailsRequest {
    private Integer weight;
    private Integer height;
    private Integer age;
    private Integer heartRate;
}
