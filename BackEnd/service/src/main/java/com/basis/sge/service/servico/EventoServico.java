package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class EventoServico {

    private final EventoRepositorio eventoRepositorio;
    private final EventoMapper eventoMapper;


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
        validaTitulo(eventoDTO.getTitulo());
        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoSalvo = eventoRepositorio.save(evento);

        return eventoMapper.toDto(eventoSalvo);
    }

    public EventoDTO atualizar(EventoDTO eventoDTO) {
        validaTitulo(eventoDTO.getTitulo());
        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoAtualizado = eventoRepositorio.save(evento);

        return eventoMapper.toDto(eventoAtualizado);
    }

    public void deletar(Integer id) {
        if(!eventoRepositorio.existsById(id)){
            throw new RegraNegocioException("Evento Não Existe");
        }
        eventoRepositorio.deleteById(id);
    }

    //Verifica se o titulo do evento já existe ou esta vazio, em caso positivo solta a exceção
    public void validaTitulo(String titulo){
        if (eventoRepositorio.existsByTitulo(titulo)){
            throw new RegraNegocioException("Um evento com esse titulo já existe");
        }
    }

}
