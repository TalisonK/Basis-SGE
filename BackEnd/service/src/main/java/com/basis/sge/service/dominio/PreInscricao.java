package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "pre_inscricao")
@Getter
@Setter
public class PreInscricao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pre_inscricao")
    @SequenceGenerator(name = "sq_pre_inscricao", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_situacao", referencedColumnName = "id")
    private TipoSituacao situacao;

}
