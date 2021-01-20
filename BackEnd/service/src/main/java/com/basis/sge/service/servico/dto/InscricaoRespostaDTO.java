package com.basis.sge.service.servico.dto;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.dominio.PreInscricao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoRespostaDTO {

    private Integer idEvento;
    private Integer idInscricao;
    private Integer idPergunta;
    private String resposta;

}