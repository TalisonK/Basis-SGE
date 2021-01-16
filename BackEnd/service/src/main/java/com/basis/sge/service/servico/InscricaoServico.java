package com.basis.sge.service.servico;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import com.basis.sge.service.servico.exception.InscricaoNotFoundException;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoServico {
    private final InscricaoRepositorio incrRepo;
    private final InscricaoMapper mapper;


    public List<PreInscricaoDTO> listar(){
        return mapper.toDto(incrRepo.findAll());
    }

    public PreInscricaoDTO buscar(Integer id){
        Optional<PreInscricao> dto = incrRepo.findById(id);
        return mapper.toDto(dto.orElseThrow(() -> new InscricaoNotFoundException("Inscrição número " + id + " não encontrada!")));
    }

    public PreInscricaoDTO criar(PreInscricaoDTO preInscricaoDTO){
        PreInscricao preInscricao = mapper.toEntity(preInscricaoDTO);
        incrRepo.save(preInscricao);
        return preInscricaoDTO;
    }
}
