package com.stackroute.producerregistrationservice.exceptions;

public class ProducerNotFoundException extends Exception {
    private String message;

    public ProducerNotFoundException (String message) {
        super(message);
    }
}
