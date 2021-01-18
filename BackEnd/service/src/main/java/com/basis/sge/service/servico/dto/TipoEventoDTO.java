package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class TipoEventoDTO {

    @NotNull
    @NotBlank(message = "Tipo Evento deve esta presente em Evento")
    private Integer id;

    private String descricao;

}
