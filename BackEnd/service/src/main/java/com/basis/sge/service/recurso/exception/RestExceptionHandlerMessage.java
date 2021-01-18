package com.basis.sge.service.recurso.exception;

import lombok.AllArgsConstructor;


import java.util.List;

@AllArgsConstructor
public class RestExceptionHandlerMessage {

    private final String message;

    private final int code;

    private final String status;

    private final String objectName;

    private final List<ObjectError> errors;
}