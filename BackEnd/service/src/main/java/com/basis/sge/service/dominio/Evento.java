package com.basis.sge.service.dominio;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Evento")
@Getter
@Setter
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_evento")
    @SequenceGenerator(name = "sq_evento", allocationSize = 1)
    private Integer id;

    @Column(name="titulo")
    private String titulo;

    @Column(name = "data_inicio")
    private Timestamp dataInicio;

    @Column(name = "data_fim")
    private Timestamp dataFim;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade_vagas")
    private Integer quantVagas;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "local")
    private String local;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_evento", referencedColumnName = "id")
    private TipoEvento tipoEvento;
}
