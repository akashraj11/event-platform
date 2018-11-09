package com.stackroute.producerregistrationservice.exceptions;

    public class TheatreAlreadyExistException extends Exception {
        private String message;

        public TheatreAlreadyExistException(String message) {
            super(message);
        }
    }