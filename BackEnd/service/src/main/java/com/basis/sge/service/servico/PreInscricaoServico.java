package com.basis.sge.service.servico;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class PreInscricaoServico {
    private final InscricaoRepositorio incrRepo;
    private final InscricaoMapper mapper;


    public List<PreInscricaoDTO> listar(){
        return mapper.toDto(incrRepo.findAll());
    }

    public PreInscricaoDTO obterPorId(Integer id){
        Optional<PreInscricao> dto = incrRepo.findById(id);
        return mapper.toDto(dto.orElseThrow(() -> new RegraNegocioException("Inscrição número " + id + " não encontrada!")));
    }

//todo Corrigir o erro "detached entity passed to persist"
    public PreInscricaoDTO criar(PreInscricaoDTO dto){

        PreInscricao preInscricao = mapper.toEntity(dto);
        incrRepo.save(preInscricao);
        return mapper.toDto(preInscricao);
    }

    public PreInscricaoDTO atualizar(PreInscricaoDTO dto) {
        return mapper.toDto(incrRepo.save(mapper.toEntity(dto)));
    }

    public PreInscricaoDTO deletar(PreInscricaoDTO dto) {
        incrRepo.delete(mapper.toEntity(dto));
        return dto;
    }
}
