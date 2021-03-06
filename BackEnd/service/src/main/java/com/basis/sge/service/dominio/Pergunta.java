package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "pergunta")
public class Pergunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pergunta")
    @SequenceGenerator(name = "sq_pergunta", allocationSize = 1)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "obrigatoriedade")
    private Boolean obrigatoriedade;

}