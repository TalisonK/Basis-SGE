package com.basis.sge.service.servico;


import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.dto.EventoDTO;

import com.basis.sge.service.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        Evento evento = eventoRepositorio.getOne(id);
        return eventoMapper.toDto(evento);
    }


    public EventoDTO criar(EventoDTO eventoDTO) {
        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoSalvo = eventoRepositorio.save(evento);

        return eventoMapper.toDto(eventoSalvo);
    }

    public EventoDTO atualizar(EventoDTO eventoDTO) {

        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoAtualizado = eventoRepositorio.save(evento);

        return eventoMapper.toDto(eventoAtualizado);
    }


    public void deletar(Integer id) {
        eventoRepositorio.deleteById(id);
    }

}
