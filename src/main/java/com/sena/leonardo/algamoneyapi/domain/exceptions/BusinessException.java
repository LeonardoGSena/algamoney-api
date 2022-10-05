package com.sena.leonardo.algamoneyapi.domain.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg) {
        super(msg);
    }
}
