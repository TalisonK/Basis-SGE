package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Getter @Setter @Table(name = "evento_pergunta")

public class EventoPergunta implements Serializable {

        @ManyToOne
        @JoinColumn(name = "id_evento", referencedColumnName = "id") //JoinColumn referencia Chave Estrangeira
        private String idEvento;

        @ManyToOne
        @JoinColumn(name = "id_pergunta", referencedColumnName = "id")
        private String idPergunta;
}