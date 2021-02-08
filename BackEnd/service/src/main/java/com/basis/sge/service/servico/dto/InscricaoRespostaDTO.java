package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InscricaoRespostaDTO implements Serializable {

    private Integer idEvento;
    private Integer idInscricao;
    private Integer idPergunta;
    private String resposta;

}