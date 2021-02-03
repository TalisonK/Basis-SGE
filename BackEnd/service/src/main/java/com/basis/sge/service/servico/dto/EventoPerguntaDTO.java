package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventoPerguntaDTO implements Serializable {

    private Integer idEvento;
    private Integer idPergunta;
}
