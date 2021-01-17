package com.basis.sge.service.servico.mapper;


import com.basis.sge.service.dominio.InscricaoResposta;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface InscricaoRespostaMapper extends EntityMapper<InscricaoRespostaDTO, InscricaoResposta>  {
}
