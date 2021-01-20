package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class TipoEventoDTO implements Serializable {

    @NotNull
    @NotBlank(message = "Tipo Evento deve esta presente em Evento")
    private Integer id;

    private String descricao;

}
