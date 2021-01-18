package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.servico.dto.EventoDTO;
import org.hibernate.annotations.CascadeType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {})
public interface EventoMapper extends  EntityMapper<EventoDTO, Evento> {

}
