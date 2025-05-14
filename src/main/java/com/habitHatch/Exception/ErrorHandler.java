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
    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<?> handleUserExistsException(InvalidValueException ue){
        ErrorResponse errorResponse=
                ErrorResponse.builder()
                        .errorCode(ue.getErrorCode())
                        .errorMessage(ue.getErrorMessage())
                        .build();
        return (new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        ErrorResponse errorResponse =
                ErrorResponse.builder().errorCode("HH_101")
                        .errorMessage(e.getMessage())
                        .build();
        return (new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
