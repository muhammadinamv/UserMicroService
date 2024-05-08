package com.project.usermicroservice.exceptions;

import lombok.Data;

@Data
public class SignUpException extends Exception{
    private String message;
    public SignUpException(String message){
        super();
        this.message=message;
    }

}
