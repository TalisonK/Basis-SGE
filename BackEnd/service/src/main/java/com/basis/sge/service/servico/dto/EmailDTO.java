package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EmailDTO implements Serializable {

    private String destinatario;

    private String corpo;

    private String assunto;

    private List<String> copias;

}
