package com.stackroute.showservice.exceptions;

public class ShowAlreadyExistsException extends Exception{

    private String message;

    public ShowAlreadyExistsException(){}

    public ShowAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
