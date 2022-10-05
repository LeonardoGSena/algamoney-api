package com.sena.leonardo.algamoneyapi.domain.exceptions;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
