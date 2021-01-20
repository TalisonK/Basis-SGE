package com.basis.sge.service.servico.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PerguntaDTO implements Serializable {

    private Integer id;

    @NotNull
    @Valid
    private String titulo;

    @NotNull
    @Valid
    private Boolean obrigatoriedade;

}
