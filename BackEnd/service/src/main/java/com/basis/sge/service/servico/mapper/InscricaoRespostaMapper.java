package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.InscricaoResposta;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface InscricaoRespostaMapper extends EntityMapper<InscricaoRespostaDTO, InscricaoResposta>  {

    @Override
    @Mapping(source = "idEvento",target = "evento.id")
    @Mapping(source = "idInscricao",target = "inscricao.id")
    @Mapping(source = "idPergunta",target = "pergunta.id")
    InscricaoResposta toEntity(InscricaoRespostaDTO dto);

    @Override
    @Mapping(source = "evento.id",target = "idEvento")
    @Mapping(source = "inscricao.id",target = "idInscricao")
    @Mapping(source = "pergunta.id",target = "idPergunta")
    InscricaoRespostaDTO toDto(InscricaoResposta inscricaoResposta);

}