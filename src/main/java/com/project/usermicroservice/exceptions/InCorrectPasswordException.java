package com.project.usermicroservice.exceptions;

public class InCorrectPasswordException extends Exception{
    String message;
    public InCorrectPasswordException(String message){
        super();
        this.message=message;
    }
}
