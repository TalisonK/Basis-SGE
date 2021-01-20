package com.basis.sge.service.servico;


import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.TipoEventoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.EmailDTO;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;


import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventoServico {

    private final EventoRepositorio eventoRepositorio;

    private final TipoEventoRepositorio tipoEventoRepositorio;

    private final EventoMapper eventoMapper;

    private final InscricaoRepositorio inscricaoRepositorio;

    private final EmailServico emailServico;

    private final UsuarioRepositorio usuarioRepositorio;

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
        validaEvento(eventoDTO);
        validaTitulo(eventoDTO.getTitulo());
        eventoDTO.setId(null);
        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoSalvo = eventoRepositorio.save(evento);

        return eventoMapper.toDto(eventoSalvo);
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
        inscricoes.forEach((inscricao) -> {
            Usuario usuarioInscrito = inscricao.getUsuario();
            //Usuario usuarioInscrito = usuarioRepositorio.findById(inscricao.getUsuario().getId()).orElseThrow(() -> new RegraNegocioException("Usuáriovv não encontrado"));
            EmailDTO mail = new EmailDTO(usuarioInscrito.getEmail(),
                    "Senhor "+ usuarioInscrito.getNome() +" O evento " + titulo + " foi atualizado, verifique sua inscrição.",
                    "Evento Atualizado");
            emailServico.sendMail(mail);
        });
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
