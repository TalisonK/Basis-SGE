package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InscricaoListagemDTO{

    private Integer id;
    private String evento;
    private String usuario;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String situacao;

}
