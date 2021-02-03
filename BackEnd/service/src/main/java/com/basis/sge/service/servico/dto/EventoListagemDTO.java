package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventoListagemDTO {

    private Integer id;
    private String titulo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String descricao;
    private Integer quantVagas;
    private Double valor;
    private String local;
    private Boolean tipoInscricao;
    private String descricaoTipoEvento;
}
