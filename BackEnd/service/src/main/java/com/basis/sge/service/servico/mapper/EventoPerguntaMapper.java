package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.servico.dto.EventoPerguntaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
public interface EventoPerguntaMapper extends EntityMapper<EventoPerguntaDTO, EventoPergunta>{

    @Override
    @Mapping(source = "evento.id", target = "idEvento")
    @Mapping(source = "pergunta.id", target = "idPergunta")
    EventoPerguntaDTO toDto(EventoPergunta eventoPergunta);

    @Override
    @Mapping(source = "idPergunta", target = "pergunta.id")
    @Mapping(source = "idEvento", target = "evento.id")
    @Mapping(source = "idEvento", target = "id.idEvento")
    @Mapping(source = "idPergunta", target = "id.idPergunta")
    EventoPergunta toEntity(EventoPerguntaDTO eventoPerguntaDTO);
}
