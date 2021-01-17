package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.InscricaoResposta;
import com.basis.sge.service.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;

import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.InscricaoRespostaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoRespostaServico {

    private final InscricaoRespostaRepositorio inscricaoRespostaRepositorio;
    private final InscricaoRespostaMapper inscricaoRespostaMapper;

    public InscricaoRespostaDTO criar(InscricaoRespostaDTO inscricaoRespostaDTO) {
        if (inscricaoRespostaDTO == null) {
            throw new RegraNegocioException("Dados inválidos");
        }
        if (inscricaoRespostaRepositorio.existsById(inscricaoRespostaDTO.getId())) {
            throw new RegraNegocioException("Id já cadastrado, tente novamente");
        }
        InscricaoResposta inscricaoResposta = inscricaoRespostaMapper.toEntity(inscricaoRespostaDTO);
        InscricaoResposta inscricaoRespostaCriada = inscricaoRespostaRepositorio.save(inscricaoResposta);
        return inscricaoRespostaMapper.toDto(inscricaoRespostaCriada);
    }

}
