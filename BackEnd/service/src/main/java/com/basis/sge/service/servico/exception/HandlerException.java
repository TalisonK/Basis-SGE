package com.basis.sge.service.servico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = RegraNegocioException.class)
    public ResponseEntity<ExceptionHandlerMessage> handleRegraNegocioException(RegraNegocioException e) {
        final ExceptionHandlerMessage exceptionHandlerMessage = new ExceptionHandlerMessage(e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionHandlerMessage, HttpStatus.BAD_REQUEST);
    }
}
