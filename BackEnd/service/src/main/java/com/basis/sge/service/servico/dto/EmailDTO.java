package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class EmailDTO implements Serializable {

    private String destinatario;

    private String corpo;

    private String assunto;

    private List<String> copias = new ArrayList<>();

    public EmailDTO(String destinatario, String corpo, String assunto, List<String> copias) {
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.assunto = assunto;
        this.copias = copias;
    }
    public EmailDTO(String destinatario, String corpo, String assunto) {
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.assunto = assunto;

    }
}
