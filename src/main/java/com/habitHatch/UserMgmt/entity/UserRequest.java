package com.habitHatch.UserMgmt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
@Schema(description = "User Request")
public class UserRequest {
    private String userId;
    private String name;
    private String gender;
    private String email;
    private Long mobileNumber;
    private String countryCode;
    private String password;
    private Boolean isPremium;
    @JsonProperty(value = "role",defaultValue = "USER")
    private String role;
}
