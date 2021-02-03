package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PreInscricaoDTO implements Serializable {

    private Integer id;
    private Integer idUsuario;
    private Integer idEvento;
    private Integer idSituacao;
}
