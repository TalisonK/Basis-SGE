package com.basis.sge.service.servico;

import com.basis.sge.service.repositorio.TipoSituacaoRepositorio;
import com.basis.sge.service.servico.dto.TipoSituacaoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.TipoSituacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TipoSituacaoServico {

    private final TipoSituacaoRepositorio rsRepo;
    private final TipoSituacaoMapper mapper;

    public List<TipoSituacaoDTO> listar(){
        return mapper.toDto(rsRepo.findAll());
    }

    public TipoSituacaoDTO obterPorId(Integer id){
        return mapper.toDto(rsRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Situação não cadastrada!")));
    }

    public TipoSituacaoDTO criar(TipoSituacaoDTO dto){
        return mapper.toDto(rsRepo.save(mapper.toEntity(dto)));
    }

    public TipoSituacaoDTO atualizar(TipoSituacaoDTO dto){
        return mapper.toDto(rsRepo.save(mapper.toEntity(dto)));
    }

    public TipoSituacaoDTO deletar(TipoSituacaoDTO dto){
        rsRepo.delete(mapper.toEntity(dto));
        return dto;
    }

}
