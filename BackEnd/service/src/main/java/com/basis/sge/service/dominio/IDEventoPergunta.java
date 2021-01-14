package com.basis.sge.service.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IDEventoPergunta implements Serializable {

    @Column(name = "id_pergunta")
    private Integer idPergunta;

    @Column(name = "id_evento")
    private Integer idEvento;
}
