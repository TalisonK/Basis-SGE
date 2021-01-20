package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class IdEventoPergunta implements Serializable {

    private Integer idEvento;
    private  Integer idPergunta;
}
