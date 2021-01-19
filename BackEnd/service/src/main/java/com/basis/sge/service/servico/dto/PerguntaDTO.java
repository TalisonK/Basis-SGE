package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PerguntaDTO {

    private String titulo;
    private Boolean obrigatoriedade;

}
