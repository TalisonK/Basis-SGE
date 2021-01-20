package com.basis.sge.service.servico.dto;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.dominio.PreInscricao;
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