package com.anudip.placement_management_system.exception;

public class ApplicationNotFound extends RuntimeException {

    public ApplicationNotFound(String message) {
        super(message);
    }
}