package com.basis.sge.service.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdEventoPergunta {

    private Integer idEvento;
    private  Integer idPergunta;
}
