package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.servico.dto.PerguntaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerguntaMapper extends EntityMapper<PerguntaDTO, Pergunta> {

}