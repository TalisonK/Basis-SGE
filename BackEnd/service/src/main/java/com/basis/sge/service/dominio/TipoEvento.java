package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tipo_evento")
@Getter
@Setter
public class TipoEvento {

    @Id
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="descricao")
    private String descricao;

}
