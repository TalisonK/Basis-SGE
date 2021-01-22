package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.repositorio.EventoPerguntaRepositorio;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.TipoEventoRepositorio;
import com.basis.sge.service.servico.dto.EmailDTO;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
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


    public List<EventoDTO> listar() {
        List<Evento> listaEvento = eventoRepositorio.findAll();

        return eventoMapper.toDto(listaEvento);
    }

    public EventoDTO obterPorId(Integer id){
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Evento não Existe"));

        return eventoMapper.toDto(evento);
    }

    public EventoDTO criar(EventoDTO eventoDTO) {
        try{
            validaEvento(eventoDTO);
            validaTitulo(eventoDTO.getTitulo());
            eventoDTO.setId(null);
            Evento evento = eventoMapper.toEntity(eventoDTO);

            List<EventoPergunta> perguntas = evento.getPerguntas();

            evento.setPerguntas(new ArrayList<>());
            eventoRepositorio.save(evento);

            perguntas.forEach(pergunta -> {
                pergunta.setEvento(evento);
            });

            eventoPerguntaRepositorio.saveAll(perguntas);

            Evento evento1 = eventoRepositorio.findById(evento.getId()).orElseThrow(() -> new RegraNegocioException("Erro ao cadastrar o evento!"));

            return eventoMapper.toDto(evento1);
        }
        catch (Exception e){
            throw new RegraNegocioException("Erro ao cadastrar evento. Erro = " + e.getMessage());
        }

    }

    public EventoDTO atualizar(EventoDTO eventoDTO) {
        validaEvento(eventoDTO);
        validaTitulo(eventoDTO.getTitulo(), eventoDTO.getId());
        validaIdEvento(eventoDTO.getId());

        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoAtualizado = eventoRepositorio.save(evento);

        notificarInscritos(evento.getTitulo(),evento.getId());

        return eventoMapper.toDto(eventoAtualizado);
    }

    public void deletar(Integer id) {
        validaIdEvento(id);
        eventoRepositorio.deleteById(id);
    }

    //---------------------------------------------------------------------
    //funções de validação e notificação

    public void notificarInscritos(String titulo,Integer id) {

        List<PreInscricao> inscricoes = inscricaoRepositorio.findAllByEventoId(id);
        List<String> distinatarios = new ArrayList();
        inscricoes.forEach((inscricao) -> {
            Usuario usuarioInscrito = inscricao.getUsuario();
            distinatarios.add(usuarioInscrito.getEmail());
        });
        EmailDTO mail = new EmailDTO("kenouen1@gmail.com",
                " O evento " + titulo + " foi atualizado, verifique sua inscrição.",
                "Evento Atualizado",distinatarios);
        emailServico.sendMail(mail);
    }

    // valida dados de evento, com exceção das datas
    public void validaEvento(EventoDTO eventoDTO){
        validaString(eventoDTO.getLocal());
        validaString(eventoDTO.getDescricao());
        validaNumero(eventoDTO.getValor());
        validaNumero(eventoDTO.getQuantVagas());
        validaTipoEvento(eventoDTO.getIdTipoEvento());

    }

    //verifica se uma string é menor que 3 caracteres, em caso não nulo
    public void validaString(String palavra){
        if (palavra!=null && palavra.length() < 3){
            throw new RegraNegocioException("Campo deve ter pelo menos 2 caracteres");
        }
    }

    //verifica se o numero é negativo caso não seja nulo
    public void validaNumero(Number num){
        if (num!=null && num.doubleValue()<0){
            throw new RegraNegocioException("Este Campo deve ser maior que 0");
        }
    }

    //Verifica se o titulo do evento já existe
    public void validaTitulo(String titulo,Integer id){
        if (eventoRepositorio.existsByTituloAndIdNot(titulo,id)){
            throw new RegraNegocioException("Um evento com esse titulo já existe");
        }
    }
    public void validaTitulo(String titulo){
        if (eventoRepositorio.existsByTitulo(titulo)){
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
