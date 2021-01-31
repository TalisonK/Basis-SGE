package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class UsuarioAutenticacaoDTO implements Serializable {

    @CPF
    @NotNull @NotBlank
    private String cpf;

    private String chave;

}
