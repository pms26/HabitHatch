package com.habitHatch.UserMgmt.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private String userId;
    private String name;
    private String email;
    private Long mobileNumber;
    private Integer weight;
    private Integer height;
    private Integer age;
    private Integer heartRate;





}
