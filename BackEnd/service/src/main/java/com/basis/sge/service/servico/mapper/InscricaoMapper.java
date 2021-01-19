package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.TipoSituacao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import com.basis.sge.service.servico.dto.TipoSituacaoDTO;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {})
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
