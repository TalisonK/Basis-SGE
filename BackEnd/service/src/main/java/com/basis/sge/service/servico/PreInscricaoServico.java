package com.basis.sge.service.servico;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.TipoSituacaoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class PreInscricaoServico {
    private final InscricaoRepositorio incrRepo;
    private final EventoRepositorio eventoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final TipoSituacaoRepositorio tsrepo;

    private final InscricaoMapper mapper;


    public List<PreInscricaoDTO> listar(){
        return mapper.toDto(incrRepo.findAll());
    }

    public PreInscricaoDTO obterPorId(Integer id){
        Optional<PreInscricao> dto = incrRepo.findById(id);
        return mapper.toDto(dto.orElseThrow(() -> new RegraNegocioException("Inscrição número " + id + " não encontrada!")));
    }

    public void idEmUso(Integer id){
        if(!incrRepo.findById(id).isPresent()){
            throw new RegraNegocioException("Inscrição não cadastrada!");
        }
    }

    public PreInscricaoDTO criar(PreInscricaoDTO dto){

        PreInscricao preInscricao = mapper.toEntity(dto);

        usuarioRepositorio.findById(dto.getIdUsuario()).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        eventoRepositorio.findById(dto.getIdEvento()).orElseThrow(() -> new RegraNegocioException("Evento nao Cadastrado!"));
        tsrepo.findById(dto.getIdSituacao()).orElseThrow(() -> new RegraNegocioException("Inscrição inexistente!"));

        incrRepo.save(preInscricao);
        return mapper.toDto(preInscricao);
    }

    public PreInscricaoDTO atualizar(PreInscricaoDTO dto) {
        return mapper.toDto(incrRepo.save(mapper.toEntity(dto)));
    }

    public void deletar(Integer id) {

        try{
            incrRepo.deleteById(id);
        }
        catch (Exception e){
            throw new RegraNegocioException("Impossivel detar, inscriçao nao cadastrada!");
        }
    }
}
