package com.basis.sge.service.servico;
import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.TipoSituacao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.service.repositorio.TipoSituacaoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.EmailDTO;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.InscricaoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.basis.sge.service.servico.mapper.InscricaoRespostaMapper;
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
    private final InscricaoRespostaServico irServico;
    private final TipoSituacaoRepositorio tsrepo;

    private final InscricaoMapper mapper;
    private final InscricaoRespostaMapper inscricaoRespostaMapper;

    private final EmailServico emailServico;


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

        Usuario usuario = usuarioRepositorio.findById(dto.getIdUsuario()).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Evento evento = eventoRepositorio.findById(dto.getIdEvento()).orElseThrow(() -> new RegraNegocioException("Evento nao Cadastrado!"));
        TipoSituacao situacao = tsrepo.findById(dto.getIdSituacao()).orElseThrow(() -> new RegraNegocioException("Inscrição inexistente!"));

        incrRepo.findAllByUsuarioId(usuario.getId()).forEach(inscricao -> {
            if (inscricao.getEvento().getId().equals(evento.getId())){
                throw new RegraNegocioException("Usuario já cadastrado no evento");
            }
        });

        incrRepo.save(preInscricao);

        emailServico.rabbitSendMail(usuario.getEmail(),
                    "Inscrição efetuado com sucesso",
                     "Inscrição bem sucedida, sua chave para acesso e atualização é: " + usuario.getChave(),
                            new ArrayList<>());

        return mapper.toDto(preInscricao);
    }

    public PreInscricaoDTO atualizar(PreInscricaoDTO dto) {
        return mapper.toDto(incrRepo.save(mapper.toEntity(dto)));
    }

    public void deletar(Integer id) {

        inscricaoRespostaMapper.toEntity(irServico.listar()).forEach(inscricaoResposta -> {
            if (inscricaoResposta.getInscricao().getId().equals(id)) {
                irServico.deletar(inscricaoResposta.getPergunta().getId(), inscricaoResposta.getInscricao().getId());
            }
        });

        try{
            PreInscricao inscricao = incrRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Inscrição nao cadastrada"));

            incrRepo.deleteById(id);



            emailServico.rabbitSendMail(inscricao.getUsuario().getEmail(),
                    "Inscrição efetuado com sucesso",
                    "Inscrição bem sucedida, sua chave para acesso e atualização é: " + inscricao.getUsuario().getChave(),
                    new ArrayList<>());
        }
        catch (Exception e){
            throw new RegraNegocioException("Impossivel detar, inscriçao nao cadastrada!");
        }
    }
}
