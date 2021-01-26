package com.basis.sge.service.builder;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.dominio.TipoEvento;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.EventoServico;
import com.basis.sge.service.servico.UsuarioServico;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.mapper.EventoMapper;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
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

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public Evento construirEntidade() throws Exception {

        Evento evento = new Evento();

        if (!usuarioRepositorio.existsByCpf("25258218074")){
            Usuario usuario = new Usuario();
            usuario.setTelefone("839999900000");
            usuario.setEmail("zedafeira@gmail.com");
            usuario.setCpf("25258218074");
            usuario.setNome("Jose Silva");
            usuario.setDataNascimento(LocalDate.now());
            usuario.setChave("a57sr28s3q28d7a");
            evento.setChaveUsuario("a57sr28s3q28d7a");
            usuarioRepositorio.save(usuario);
        }else {
            evento.setChaveUsuario("a57sr28s3q28d7a");
        }

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
        Evento evento = eventoRepositorio.findById(id).orElse(null);

        return evento;
    }

    public void limparBanco()
    {
        eventoRepositorio.deleteAll();
    }

    public Object converterToDto(Evento Evento)
    {
        return eventoMapper.toDto(Evento);
    }
    public Evento criar(Evento evento){
        return eventoRepositorio.save(evento);
    }
}
