package com.basis.sge.service.servico.dto;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.TipoSituacao;
import com.basis.sge.service.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreInscricaoDTO {

    private Integer id;
    private Usuario usuario;
    private Evento evento;
    private TipoSituacao situacao;
}
