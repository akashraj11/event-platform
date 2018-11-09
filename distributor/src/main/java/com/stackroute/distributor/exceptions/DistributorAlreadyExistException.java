package com.stackroute.distributor.exceptions;

public class DistributorAlreadyExistException extends Exception {
    public String message;

    public DistributorAlreadyExistException(String message) {
        super(message);
    }
}
