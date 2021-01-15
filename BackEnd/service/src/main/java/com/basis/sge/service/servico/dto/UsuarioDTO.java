package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String dataNascimento;

}
