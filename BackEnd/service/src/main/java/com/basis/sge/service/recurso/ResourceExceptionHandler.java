package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.exception.InscricaoNotFoundException;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(InscricaoNotFoundException.class)
    public ResponseEntity<RegraNegocioException> objectNotFound(InscricaoNotFoundException e, HttpServletRequest request){
        RegraNegocioException err = new RegraNegocioException(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
