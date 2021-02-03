package com.basis.sge.service.builder;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.TipoEvento;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.EventoServico;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.mapper.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Component
public class EventoBuilder extends ConstrutorDeEntidade<Evento> {

    @Autowired
    private EventoServico eventoServico;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Override
    public Evento construirEntidade() throws Exception {

        Evento evento = new Evento();
        evento.setTitulo("Show do Zé");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now());
        evento.setDescricao("O Show do Zé vai ter Forró e Muito Mais!");
        evento.setLocal("Shopping Partage");
        evento.setTipoInscricao(false);
        evento.setQuantVagas(10);
        evento.setValor(10.00);
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setId(1);
        evento.setTipoEvento(tipoEvento);
        evento.setPerguntas(new ArrayList<>());

        return evento;
    }

    @Override
    public Evento persistir(Evento entidade)
    {
        EventoDTO dto = eventoServico.criar(eventoMapper.toDto(entidade));
        return eventoMapper.toEntity(dto);
    }

    @Override
    public List<Evento> obterTodos()
    {
        return eventoRepositorio.findAll();
    }

    @Override
    public Evento obterPorId(Integer id)
    {
        return eventoRepositorio.findById(id).orElse(null);
    }

}
