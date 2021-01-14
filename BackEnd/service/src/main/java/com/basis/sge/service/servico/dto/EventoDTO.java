package com.basis.sge.service.servico.dto;

import com.basis.sge.service.dominio.TipoEvento;


import java.time.LocalDateTime;

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
