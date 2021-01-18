package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
=======
>>>>>>> 8bb90a9a2782f8dde3815ec290b07643c1d15f7e


@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    private String nome;
<<<<<<< HEAD

    @CPF
    @NotNull
    private String cpf;

    @Email
    @NotNull
    private String email;
   // @Pattern("(\\d{2}) \\d{4}-\\d{4}")
=======
    private String cpf;
    private String email;
>>>>>>> 8bb90a9a2782f8dde3815ec290b07643c1d15f7e
    private String telefone;
    private String dataNascimento;

}
