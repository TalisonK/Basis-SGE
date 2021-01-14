package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Getter @Setter @Table(name = "pergunta")

public class Pergunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pergunta")
    @SequenceGenerator(name = "SQ_pergunta")
    private Integer Id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "obrigatoriedade")
    private Boolean obrigatoriedade;
}


