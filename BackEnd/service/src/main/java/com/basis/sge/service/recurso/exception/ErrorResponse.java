package com.basis.sge.service.recurso.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private final String message;

    private final int code;

    private final String status;

    private final String objectName;

    private final List<ObjectError> errors;

}
