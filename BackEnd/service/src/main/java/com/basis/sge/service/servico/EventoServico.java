package com.basis.sge.service.servico;


import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.dto.EventoDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventoServico {

    private final EventoRepositorio eventoRepositorio;

    public List<EventoDTO> listar() {
        List<Evento> listaEvento = eventoRepositorio.findAll();

        return null;
    }


    public ResponseEntity<EventoDTO> obterPorId(Integer id){
        Evento evento = eventoRepositorio.getOne(id);
        return null;
    }
    /*

    public EventoDTO criar(EventoDTO eventoDTO) {
        Evento evento = eventoRepositorio.save(eventoDTO);
        return null;
    }

    public EventoDTO atualizar(EventoDTO eventoDTO) {

        Evento evento = eventoRepositorio.save(eventoDTO);
        return null;
    }


    public void deletar(Integer id) {

    }*/

}
