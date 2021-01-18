package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "inscricao_resposta")
@Getter
@Setter
public class InscricaoResposta implements Serializable {

    @EmbeddedId
    private IdInscricaoResposta id;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @ManyToOne
    @MapsId("idPreInscricao")
    @JoinColumn(name = "id_inscricao", referencedColumnName = "id")
    private PreInscricao inscricao;

    @ManyToOne
    @MapsId("idPergunta")
    @JoinColumn(name = "id_pergunta", referencedColumnName = "id")
    private Pergunta pergunta;

    @Column(name = "resposta")
    private String resposta;
}
