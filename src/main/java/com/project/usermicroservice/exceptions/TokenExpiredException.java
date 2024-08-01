package com.project.usermicroservice.exceptions;

public class TokenExpiredException extends Exception{
    String message;
    public TokenExpiredException(String message){
        super();
        this.message = message;
    }
}
