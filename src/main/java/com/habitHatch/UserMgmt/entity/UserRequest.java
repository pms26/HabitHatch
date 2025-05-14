package com.habitHatch.UserMgmt.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    private Long mobileNumber;
    private String countryCode;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one special character")
    private String password;
    private Boolean isPremium;
}
