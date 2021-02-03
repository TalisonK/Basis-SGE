package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.servico.dto.InscricaoListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InscricaoListagemMapper extends EntityMapper<InscricaoListagemDTO, PreInscricao>{

    @Override
    @Mapping(source = "evento.titulo",     target = "evento")
    @Mapping(source = "evento.dataInicio", target = "dataInicio")
    @Mapping(source = "evento.dataFim",    target = "dataFim")
    @Mapping(source = "situacao.situacao", target = "situacao")
    @Mapping(source = "usuario.cpf",       target = "usuario")
    InscricaoListagemDTO toDto(PreInscricao preInscricao);

    @Override
    @Mapping(source = "evento", target = "evento.titulo")
    @Mapping(source = "dataInicio", target = "evento.dataInicio")
    @Mapping(source = "dataFim", target = "evento.dataFim")
    @Mapping(source = "situacao", target = "situacao.situacao")
    @Mapping(source = "usuario", target = "usuario.cpf")
    PreInscricao toEntity(InscricaoListagemDTO inscricaoListagemDTO);

}
