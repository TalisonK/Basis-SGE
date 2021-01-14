package com.basis.sge.service.dominio;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="evento")
@Getter
@Setter
public class Evento {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    @Column(name="titulo")
    private String titulo;

    @NotNull
    @Column(name="data_inicio")
    private LocalDateTime dataInicio;

    @NotNull
    @Column(name="data_fim")
    private LocalDateTime dataFim;


    @Column(name="descricao")
    private String descricao;


    @Column(name="quantidade_vagas")
    private Integer quantVagas;


    @Column(name="valor")
    private Double valor;


    @Column(name="local")
    private String local;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_tipo_evento",referencedColumnName = "id")
    private TipoEvento tipoEvento;
}
