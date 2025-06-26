package com.habitHatch.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    private String userId;
    private String password;

}
