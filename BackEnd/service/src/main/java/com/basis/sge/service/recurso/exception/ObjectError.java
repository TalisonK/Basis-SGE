package com.basis.sge.service.recurso.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
public class ObjectError {

    private final String message;

    private final String field;

    private final Object parameter;

}