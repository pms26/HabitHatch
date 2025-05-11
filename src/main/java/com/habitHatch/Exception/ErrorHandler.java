package com.habitHatch.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MandatoryParameterException.class)
    public ResponseEntity<?> handleMandatoryParameterException(MandatoryParameterException me){
        ErrorResponse errorResponse=
                ErrorResponse.builder()
                        .errorCode(me.getErrorCode())
                        .errorMessage(me.getErrorMessage())
                        .build();
        return (new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST));
    }

}
