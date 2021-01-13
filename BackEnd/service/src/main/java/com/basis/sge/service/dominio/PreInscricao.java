package com.basis.sge.service.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
public class PreInscricao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Integer idUsuario;

    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    private Integer idEvento;

    @JoinColumn(name = "id_situacao", referencedColumnName = "id_id_situacao")
    private Integer idSituacao;





}
