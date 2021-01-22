package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.servico.dto.EventoDTO;
import org.hibernate.annotations.CascadeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {EventoPerguntaMapper.class})
public interface EventoMapper extends  EntityMapper<EventoDTO, Evento> {

    @Override
    //@Mapping(source = "idPerguntas",target="perguntas.id")
    @Mapping(source = "idTipoEvento",target = "tipoEvento.id")
    Evento toEntity(EventoDTO eventoDTO);

    @Override
    //@Mapping(source = "perguntas.id", target = "idPerguntas")
    @Mapping(source = "tipoEvento.id",target = "idTipoEvento")
    EventoDTO toDto(Evento evento);

}
