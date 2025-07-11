package com.habitHatch.UserMgmt.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
@Schema(description = "User Details Request")
public class UserDetailsRequest {

    private Integer weight;
    private Integer height;
    private Integer age;
    private Integer heartRate;
}
