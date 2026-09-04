package com.baz.vehicle.rent.exception;

public class DuplicateRegistrationException extends RuntimeException {

    public DuplicateRegistrationException(String registrationNumber) {
        super("A vehicle with registration number '" + registrationNumber + "' already exists");
    }
}
