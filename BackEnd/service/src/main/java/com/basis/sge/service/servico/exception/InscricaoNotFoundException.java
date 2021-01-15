package com.basis.sge.service.servico.exception;

public class InscricaoNotFoundException extends RuntimeException{

    public InscricaoNotFoundException(String msg) {
        super(msg);
    }

    public InscricaoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
