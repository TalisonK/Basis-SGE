package com.basis.sge.service.servico;


import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.dto.EventoDTO;

import com.basis.sge.service.servico.dto.TipoEventoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.EventoMapper;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;


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
        if (eventoRepositorio.existsByTitulo(eventoDTO.getTitulo())){
            throw new RegraNegocioException("Um evento com esse titulo já existe");
        }
        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento eventoSalvo = eventoRepositorio.save(evento);

        return eventoMapper.toDto(eventoSalvo);
    }

    public EventoDTO atualizar(EventoDTO eventoDTO) {
        if (eventoRepositorio.existsByTituloAndIdNot(eventoDTO.getTitulo(),eventoDTO.getId())){
            throw new RegraNegocioException("Um evento com esse titulo já existe");
        }
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

}
