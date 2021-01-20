package com.basis.sge.service.servico.dto;


import lombok.Getter;
import lombok.Setter;


import javax.validation.Valid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventoDTO implements Serializable {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 2)
    private String titulo;

    @NotNull
    @Valid
    private LocalDateTime dataInicio;

    @NotNull
    @Valid
    private LocalDateTime dataFim;

    private String descricao;

    private Integer quantVagas;

    private Double valor;

    private String local;

    @NotNull
    private Boolean tipoInscricao;

    @NotNull
    private Integer idTipoEvento;

    private List<EventoPerguntaDTO> perguntas;

}
