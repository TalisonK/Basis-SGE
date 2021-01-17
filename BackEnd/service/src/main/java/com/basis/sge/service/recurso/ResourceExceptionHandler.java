package com.basis.sge.service.recurso;
import com.basis.sge.service.servico.exception.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class ResourceExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<UsuarioNotFoundException> objectNotFound(UsuarioNotFoundException e, HttpServletRequest request){
        UsuarioNotFoundException error = new UsuarioNotFoundException(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
