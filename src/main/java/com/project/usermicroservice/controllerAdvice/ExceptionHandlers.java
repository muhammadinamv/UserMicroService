package com.project.usermicroservice.controllerAdvice;

import com.project.usermicroservice.exceptions.SignUpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(value= SignUpException.class)
    public ResponseEntity<String> signUpException(SignUpException signUpException){
        ResponseEntity<String> responseEntity= new ResponseEntity<String>(signUpException.getMessage(),
                HttpStatus.EXPECTATION_FAILED);
        return responseEntity;
    }
}
