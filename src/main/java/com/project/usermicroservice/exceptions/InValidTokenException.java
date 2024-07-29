package com.project.usermicroservice.exceptions;

public class InValidTokenException extends Exception{
    String message;
    public InValidTokenException(String message){
        super();
        this.message = message;
    }
}
