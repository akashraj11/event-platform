package com.stackroute.distributor.exceptions;

public class MovieAlreadyExistException extends Exception {
    public String message;

    public MovieAlreadyExistException(String message) {
        super(message);
    }

    }
