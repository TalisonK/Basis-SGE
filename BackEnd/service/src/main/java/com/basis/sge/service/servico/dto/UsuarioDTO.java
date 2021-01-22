package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private Integer id;

    @NotNull @NotBlank
    private String nome;

    @CPF
    @NotNull @NotBlank
    private String cpf;

    @Email
    @NotNull @NotBlank
    private String email;

    private String telefone;
  
    @NotNull
    private LocalDate dataNascimento;

}
