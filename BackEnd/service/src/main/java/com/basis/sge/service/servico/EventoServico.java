package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.mensagem.EmailMensagem;
import com.basis.sge.service.repositorio.*;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.dto.EventoListagemDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.EventoListagemMapper;
import com.basis.sge.service.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class EventoServico {

    private final EventoRepositorio eventoRepositorio;

    private final EventoPerguntaRepositorio eventoPerguntaRepositorio;

    private final TipoEventoRepositorio tipoEventoRepositorio;

    private final EventoMapper eventoMapper;

    private final InscricaoRepositorio inscricaoRepositorio;

    private final EmailServico emailServico;

    private final EventoListagemMapper eventoListagemMapper;

    public List<EventoListagemDTO> listar() {
        List<Evento> listaEvento = eventoRepositorio.findAll();

        return eventoListagemMapper.toDto(listaEvento);
    }

    public EventoDTO obterPorId(Integer id){
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Evento não Existe"));

        return eventoMapper.toDto(evento);
    }

    public EventoDTO criar(EventoDTO eventoDTO) {
        validaEvento(eventoDTO);

        validaTitulo(eventoDTO.getTitulo());
        eventoDTO.setId(null);
        Evento evento = eventoMapper.toEntity(eventoDTO);

        List<EventoPergunta> perguntas = evento.getPerguntas();

        evento.setPerguntas(new ArrayList<>());
        eventoRepositorio.save(evento);

        if (perguntas != null && !perguntas.isEmpty()) {
            perguntas.forEach(pergunta -> pergunta.setEvento(evento));
            eventoPerguntaRepositorio.saveAll(perguntas);
        }
        return eventoMapper.toDto(evento);
    }
  
    public EventoDTO atualizar(EventoDTO eventoDTO) {
        validaEvento(eventoDTO);
        validaTitulo(eventoDTO.getTitulo(), eventoDTO.getId());
        validaIdEvento(eventoDTO.getId());

        Evento evento = eventoMapper.toEntity(eventoDTO);
        notificarInscritos(evento.getTitulo(),evento.getId(),0);
        Evento eventoAtualizado = eventoRepositorio.save(evento);
        return eventoMapper.toDto(eventoAtualizado);
    }

    public void deletar(Integer id) {
        validaIdEvento(id);
        Evento evento = eventoRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Evento não existe"));
        notificarInscritos(evento.getTitulo(),id,1);
        eventoRepositorio.deleteById(id);
    }

    //---------------------------------------------------------------------
    //funções de validação e notificação
    //Tipo 0 evento editado e tipo 1 evento cancelado
    public void notificarInscritos(String titulo,Integer id,Integer tipo) {
        Evento evento = eventoRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Evento não existe"));
        List<PreInscricao> inscricoes = inscricaoRepositorio.findAllByEvento(evento);
        List<String> destinatarios = new ArrayList();
        inscricoes.forEach((inscricao) -> {
            Usuario usuarioInscrito = inscricao.getUsuario();
            destinatarios.add(usuarioInscrito.getEmail());
        });
        EmailMensagem emailMensagem = new EmailMensagem();
        if(tipo==0) {
            emailServico.rabbitSendMail("kenouen1@gmail.com",
                    "Evento Atualizado",emailMensagem.messageEventoEditado("Inscrito(a)", titulo), destinatarios);
        }else if(tipo==1) {
            emailServico.rabbitSendMail("kenouen1@gmail.com",
                    "Evento Atualizado",emailMensagem.messageEventoCancelado("Inscrito(a)", titulo), destinatarios);
        }
    }
    // valida dados de evento, com exceção das datas
    public void validaEvento(EventoDTO eventoDTO){
        validaNumero(eventoDTO.getValor());
        validaNumero(eventoDTO.getQuantVagas());
        validaTipoEvento(eventoDTO.getIdTipoEvento());

    }

    //verifica se o numero é negativo caso não seja nulo
    public void validaNumero(Number num){
        if (num!=null && num.doubleValue()<0){
            throw new RegraNegocioException("Este Campo deve ser maior que 0");
        }
    }

    //Verifica se o titulo do evento já existe
    public void validaTitulo(String titulo,Integer id){
        if (eventoRepositorio.existsByTituloAndIdNot(titulo,id).equals(true)){
            throw new RegraNegocioException("Um evento com esse titulo já existe");
        }
    }
    public void validaTitulo(String titulo){
        if (eventoRepositorio.existsByTitulo(titulo).equals(true)){
            throw new RegraNegocioException("Um evento com esse titulo já existe");
        }
    }

    //verifica se o tipo de evento existe no banco
    public void validaTipoEvento(Integer idTipoEvento) {
        if(!tipoEventoRepositorio.existsById(idTipoEvento)){
            throw new RegraNegocioException("Esse Tipo de Evento não existe");
        }
    }

    //verifica se o evento correspondente ao id existe
    public void validaIdEvento(Integer idEvento){
        if(!eventoRepositorio.existsById(idEvento)){
            throw new RegraNegocioException("Evento Não Existe");
        }
    }
}
