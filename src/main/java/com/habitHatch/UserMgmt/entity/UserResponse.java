package com.habitHatch.UserMgmt.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User Response")
public class UserResponse {
    private String userId;
    private String name;
    private String email;
    private Long mobileNumber;
    private Boolean isPremium;
}
