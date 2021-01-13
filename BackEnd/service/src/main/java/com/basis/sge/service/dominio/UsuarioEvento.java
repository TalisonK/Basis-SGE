package com.basis.sge.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Getter @Setter @Table(name = "usuario_evento")
public class UsuarioEvento implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "chave_usuario")
    private String chaveUsuario;


}
