package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Getter
@Setter
public class InscricaoResposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    private Integer idEvento;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Integer idUsuario;

    @JoinColumn(name = "id_inscricao", referencedColumnName = "id_inscricao")
    private Integer idInscricao;
}
