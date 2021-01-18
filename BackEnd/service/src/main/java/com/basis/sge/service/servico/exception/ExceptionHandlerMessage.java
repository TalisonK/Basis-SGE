package com.basis.sge.service.servico.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.io.Serializable;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Data
public class ExceptionHandlerMessage implements Serializable {

    private static final long serialVersionUID = -8536454302295463287L;

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
}