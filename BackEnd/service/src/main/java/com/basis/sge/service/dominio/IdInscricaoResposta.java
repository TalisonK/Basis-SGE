package com.basis.sge.service.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class IdInscricaoResposta implements Serializable {

    public IdInscricaoResposta() {
    }

    public IdInscricaoResposta(Integer idPergunta, Integer idEvento, Integer idPreInscricao) {
        this.idPergunta = idPergunta;
        this.idEvento = idEvento;
        this.idPreInscricao = idPreInscricao;
    }

    @Column(name = "id_pergunta")
    private Integer idPergunta;

    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "id_inscricao")
    private Integer idPreInscricao;


}
