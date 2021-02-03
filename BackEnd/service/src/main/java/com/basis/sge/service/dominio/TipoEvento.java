package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tipo_evento")
@Getter
@Setter
public class TipoEvento implements Serializable {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="descricao")
    private String descricao;

}
