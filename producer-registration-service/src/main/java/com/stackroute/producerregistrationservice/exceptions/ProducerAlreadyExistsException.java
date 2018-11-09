package com.stackroute.producerregistrationservice.exceptions;

public class ProducerAlreadyExistsException extends Exception {
    private String message;

    public ProducerAlreadyExistsException(String message) {
        super(message);
    }
}
