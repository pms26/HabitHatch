package com.habitHatch.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InvalidValueException extends Exception {

    private String errorCode;
    private String errorMessage;
    public InvalidValueException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
