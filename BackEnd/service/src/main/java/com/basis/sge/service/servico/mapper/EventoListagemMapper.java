package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.servico.dto.EventoListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
public interface EventoListagemMapper extends  EntityMapper<EventoListagemDTO, Evento> {

        @Override
        @Mapping(source = "tipoEvento.descricao",target = "descricaoTipoEvento")
        EventoListagemDTO toDto(Evento evento);

}

