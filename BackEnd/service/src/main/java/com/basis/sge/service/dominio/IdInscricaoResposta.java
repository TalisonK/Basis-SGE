package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class IdInscricaoResposta implements Serializable {


    @Column(name = "id_pergunta")
    private Integer idPergunta;

    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "id_inscricao")
    private Integer idPreInscricao;


}
