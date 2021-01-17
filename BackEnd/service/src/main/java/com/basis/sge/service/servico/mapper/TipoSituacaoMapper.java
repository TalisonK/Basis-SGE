package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.TipoSituacao;
import com.basis.sge.service.servico.dto.TipoSituacaoDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",uses = {})
public interface TipoSituacaoMapper extends EntityMapper<TipoSituacaoDTO, TipoSituacao>{
}
