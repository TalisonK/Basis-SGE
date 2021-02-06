package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.InscricaoResposta;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.mensagem.EmailMensagem;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.service.repositorio.TipoSituacaoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.ConjuntoPerguntaRespostaDTO;
import com.basis.sge.service.servico.dto.InscricaoListagemDTO;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.InscricaoListagemMapper;
import com.basis.sge.service.servico.mapper.InscricaoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.basis.sge.service.servico.mapper.InscricaoRespostaMapper;
import lombok.RequiredArgsConstructor;
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
    private final TipoSituacaoRepositorio tipoSituacaoRepositorio;

    private final InscricaoMapper inscricaoMapper;
    private final InscricaoRespostaMapper inscricaoRespostaMapper;
    private final InscricaoListagemMapper inscricaoListagemMapper;
    private final InscricaoRespostaRepositorio inscricaoRespostaRepositorio;

    private final EmailServico emailServico;


    public List<InscricaoListagemDTO> listar(){
        return inscricaoListagemMapper.toDto(incrRepo.findAll());
    }

    public PreInscricaoDTO obterPorId(Integer id){
        Optional<PreInscricao> dto = incrRepo.findById(id);
        return inscricaoMapper.toDto(dto.orElseThrow(() -> new RegraNegocioException("Inscrição número " + id + " não encontrada!")));
    }

    public List<PreInscricaoDTO> obterPorUsuarioId(Integer id){
        try {
            return inscricaoMapper.toDto(incrRepo.findAllByUsuarioId(id));
        }
        catch (Exception e){
            throw new RegraNegocioException("Usuario não inscrito em eventos");
        }
    }

    public void idEmUso(Integer id){
        if(!incrRepo.findById(id).isPresent()){
            throw new RegraNegocioException("Inscrição não cadastrada!");
        }
    }

    public PreInscricaoDTO criar(PreInscricaoDTO dto){

        PreInscricao preInscricao = inscricaoMapper.toEntity(dto);

        Usuario usuario = usuarioRepositorio.findById(dto.getIdUsuario()).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Evento evento = eventoRepositorio.findById(dto.getIdEvento()).orElseThrow(() -> new RegraNegocioException("Evento nao Cadastrado!"));
        if(!tipoSituacaoRepositorio.existsById(dto.getIdSituacao())){ throw new RegraNegocioException("Inscrição inexistente!");}

        incrRepo.findAllByUsuarioId(usuario.getId()).forEach(inscricao -> {
            if (inscricao.getEvento().getId().equals(evento.getId())){
                throw new RegraNegocioException("Usuario já cadastrado no evento");
            }
        });

        incrRepo.save(preInscricao);
        EmailMensagem emailMensagem = new EmailMensagem();
        emailServico.rabbitSendMail(usuario.getEmail(),
                    "Inscrição efetuado com sucesso",
                     emailMensagem.messageInscricaoAceita(usuario.getNome(),evento.getTitulo()),
                            new ArrayList<>());

        return inscricaoMapper.toDto(preInscricao);
    }

    public InscricaoListagemDTO atualizar(PreInscricaoDTO dto) {


        return inscricaoListagemMapper.toDto(incrRepo.save(inscricaoMapper.toEntity(dto)));
    }

    public void deletar(Integer id) {

        inscricaoRespostaMapper.toEntity(irServico.listar()).forEach(inscricaoResposta -> {
            if (inscricaoResposta.getInscricao().getId().equals(id)) {
                irServico.deletar(inscricaoResposta.getPergunta().getId(), inscricaoResposta.getInscricao().getId());
            }
        });

        try{
            PreInscricao inscricao = incrRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Inscrição nao cadastrada"));
            Usuario usuario = inscricao.getUsuario();
            Evento evento = inscricao.getEvento();
            incrRepo.deleteById(id);
            EmailMensagem emailMensagem = new EmailMensagem();
            emailServico.rabbitSendMail(inscricao.getUsuario().getEmail(),
                    "Inscrição removida com sucesso",
                    emailMensagem.messageInscricaoAceita(usuario.getNome(),evento.getTitulo()),
                    new ArrayList<>());
        }
        catch (Exception e){
            throw new RegraNegocioException("Impossivel deletar, inscriçao nao cadastrada!");
        }
    }

    public List<ConjuntoPerguntaRespostaDTO> buscarPerguntasRespostas(PreInscricaoDTO dto){

        List<ConjuntoPerguntaRespostaDTO> conjuntos = new ArrayList<>();

        PreInscricao preInscricao = incrRepo.findById(dto.getId()).orElseThrow(() -> new RegraNegocioException("Inscricao não existe"));
        List<InscricaoResposta> inscricaoRespostaList = inscricaoRespostaRepositorio.findAllByInscricao(preInscricao);
        inscricaoRespostaList.forEach(inscricaoResposta ->{
            ConjuntoPerguntaRespostaDTO conjuntoPerguntaRespostaDTO = new ConjuntoPerguntaRespostaDTO();
            conjuntoPerguntaRespostaDTO.setPergunta(inscricaoResposta.getPergunta().getTitulo());
            conjuntoPerguntaRespostaDTO.setResposta(inscricaoResposta.getResposta());
            conjuntos.add(conjuntoPerguntaRespostaDTO);
        });

        return conjuntos;
    }
}
