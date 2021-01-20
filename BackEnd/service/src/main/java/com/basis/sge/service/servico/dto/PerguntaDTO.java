package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PerguntaDTO {

    private Integer id;

    @NotNull
    @Valid
    private String titulo;

    @NotNull
    @Valid
    private Boolean obrigatoriedade;

}
