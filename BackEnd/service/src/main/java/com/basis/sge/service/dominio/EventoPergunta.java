package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "evento_pergunta")
@Getter
@Setter
public class EventoPergunta implements Serializable {

    @EmbeddedId
    private IDEventoPergunta id;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento idEvento;

    @ManyToOne
    @MapsId("idPergunta")
    @JoinColumn(name = "id_pergunta", referencedColumnName = "id")
    private Pergunta idPergunta;
}
