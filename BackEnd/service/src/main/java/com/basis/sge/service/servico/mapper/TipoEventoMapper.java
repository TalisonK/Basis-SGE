package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.TipoEvento;
import com.basis.sge.service.servico.dto.TipoEventoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoEventoMapper extends EntityMapper<TipoEventoDTO, TipoEvento>{
}
