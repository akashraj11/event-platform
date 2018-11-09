package com.stackroute.producerregistrationservice.exceptions;


    public class TheatreNotFoundException extends Exception {
        private String message;

        public TheatreNotFoundException(String message) {
            super(message);
        }
    }
