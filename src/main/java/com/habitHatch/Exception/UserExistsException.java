package com.habitHatch.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserExistsException extends Exception {

    private String errorCode;
    private String errorMessage;
    public UserExistsException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
