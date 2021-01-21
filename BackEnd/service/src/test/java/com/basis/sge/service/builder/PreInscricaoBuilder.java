package com.basis.sge.service.builder;

import com.basis.sge.service.dominio.*;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


@Component

public class PreInscricaoBuilder extends ConstrutorDeEntidade<PreInscricao>{

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private InscricaoMapper mapper;


    @Override
    public PreInscricao construirEntidade() throws ParseException {

        PreInscricao preInscricao = new PreInscricao();

        TipoSituacao tipoSituacao = new TipoSituacao();
        tipoSituacao.setId(1);
        preInscricao.setSituacao(tipoSituacao);

        Evento evento = new Evento();
        evento.setId(null);
        evento.setTitulo("asdas");
        evento.setDataFim(LocalDateTime.now());
        evento.setDataInicio(LocalDateTime.now());
        evento.setTipoEvento(new TipoEvento());
        evento.getTipoEvento().setId(1);

        preInscricao.setEvento(evento);

        Usuario usuario = new Usuario();
        usuario.setId(null);
        usuario.setNome("a");
        usuario.setCpf("08502610406");
        usuario.setEmail("joao@gmail.com");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setChave("asdasdadadasdasdasdasd");
        preInscricao.setUsuario(usuario);

        return preInscricao;
    }

    @Override
    protected PreInscricao persistir(PreInscricao entidade) {

        buildDependencias(entidade);

        return inscricaoRepositorio.save(entidade);
    }

    @Override
    protected Collection<PreInscricao> obterTodos() {
        return inscricaoRepositorio.findAll();
    }

    @Override
    protected PreInscricao obterPorId(Integer id) {
        return inscricaoRepositorio.findById(id).orElse(null);
    }


    public void buildDependencias(PreInscricao preInscricao){

        usuarioRepositorio.save(preInscricao.getUsuario());

        eventoRepositorio.save(preInscricao.getEvento());

    }
}
