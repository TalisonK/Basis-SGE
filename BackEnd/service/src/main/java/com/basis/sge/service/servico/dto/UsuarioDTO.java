package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    private String nome;

    @CPF
    @NotNull
    private String cpf;

    @Email
    @NotNull
    private String email;
   // @Pattern("(\\d{2}) \\d{4}-\\d{4}")
    private String telefone;
    private LocalDate dataNascimento;

}
