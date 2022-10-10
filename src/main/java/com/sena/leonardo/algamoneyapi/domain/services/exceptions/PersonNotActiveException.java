package com.sena.leonardo.algamoneyapi.domain.services.exceptions;

public class PersonNotActiveException extends RuntimeException {
    public PersonNotActiveException(String message) {
        super(message);
    }
}
