package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {})
public interface InscricaoMapper extends EntityMapper<PreInscricaoDTO, PreInscricao> {
}
