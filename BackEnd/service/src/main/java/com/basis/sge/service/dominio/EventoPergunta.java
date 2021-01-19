package com.basis.sge.service.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;

public class EventoPergunta {

    @EmbeddedId
    private IdEventoPergunta id;

    @Column(name = "id_evento")
    @MapsId("idEvento")
    private Evento evento;

    @Column(name = "id_pergunta")
    @MapsId("idPergunta")
    private Pergunta pergunta;

}
