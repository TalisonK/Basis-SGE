package com.basis.sge.service.servico.dto;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.dominio.PreInscricao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoRespostaDTO {

    private Evento evento;
    private PreInscricao inscricao;
    private Pergunta pergunta;
    private String resposta;

}