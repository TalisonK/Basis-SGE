package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="evento")
@Getter
@Setter
public class Evento implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sq_evento")
    @SequenceGenerator(name = "sq_evento", allocationSize = 1)
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

    /*
    @NotNull
    @Column(name = "chave_usuario")
    private String chaveUsuario;
    */

    @Column(name="quantidade_vagas")
    private Integer quantVagas;

    @Column(name="valor")
    private Double valor;

    @Column(name="local")
    private String local;

    @NotNull
    @Column(name = "tipo_inscricao")
    private Boolean tipoInscricao;

    @NotNull
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="id_tipo_evento",referencedColumnName = "id")
    private TipoEvento tipoEvento;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "evento")
    private List<EventoPergunta> perguntas;
}
