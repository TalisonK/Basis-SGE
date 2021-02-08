package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InscricaoMapper extends EntityMapper<PreInscricaoDTO, PreInscricao> {

    @Override
    @Mapping(source = "idUsuario",target = "usuario.id")
    @Mapping(source = "idEvento",target = "evento.id")
    @Mapping(source = "idSituacao",target = "situacao.id")
    PreInscricao toEntity(PreInscricaoDTO dto);

    @Override
    @Mapping(source = "usuario.id",target = "idUsuario")
    @Mapping(source = "evento.id",target = "idEvento")
    @Mapping(source = "situacao.id",target = "idSituacao")
    PreInscricaoDTO toDto(PreInscricao preInscricao);
}
