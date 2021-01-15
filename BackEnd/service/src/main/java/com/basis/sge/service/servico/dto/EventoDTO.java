package com.basis.sge.service.servico.dto;

import com.basis.sge.service.dominio.TipoEvento;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class EventoDTO {


    private Integer id;


    private String titulo;


    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;



    private String descricao;


    private Integer quantVagas;



    private Double valor;



    private String local;



    private TipoEvento tipoEvento;
}
