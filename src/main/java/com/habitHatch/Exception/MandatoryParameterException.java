package com.habitHatch.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MandatoryParameterException extends Exception {
    private String errorCode;
    private String errorMessage;
    public MandatoryParameterException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
