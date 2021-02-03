package com.basis.sge.service.builder;

import com.basis.sge.service.dominio.*;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.servico.EventoServico;
import com.basis.sge.service.servico.UsuarioServico;
import com.basis.sge.service.servico.mapper.EventoMapper;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;


@Component
public class PreInscricaoBuilder extends ConstrutorDeEntidade<PreInscricao>{

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private EventoServico eventoServico;

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;


    @Override
    public PreInscricao construirEntidade() throws Exception {

        PreInscricao preInscricao = new PreInscricao();

        TipoSituacao tipoSituacao = new TipoSituacao();
        tipoSituacao.setId(1);
        preInscricao.setSituacao(tipoSituacao);

        Evento evento = eventoBuilder.construirEntidade();

        Usuario usuario = usuarioBuilder.construirEntidade();

        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        return preInscricao;
    }

    @Override
    protected PreInscricao persistir(PreInscricao entidade) {

        usuarioServico.criar(usuarioMapper.toDto(entidade.getUsuario()));

        eventoServico.criar(eventoMapper.toDto(entidade.getEvento()));

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

        usuarioServico.criar(usuarioMapper.toDto(preInscricao.getUsuario()));

        eventoRepositorio.save(preInscricao.getEvento());

    }
}
