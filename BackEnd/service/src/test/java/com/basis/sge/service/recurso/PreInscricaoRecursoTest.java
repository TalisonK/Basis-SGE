package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.EventoBuilder;
import com.basis.sge.service.builder.PreInscricaoBuilder;
import com.basis.sge.service.builder.UsuarioBuilder;
import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.servico.PreInscricaoServico;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@Transactional
class PreInscricaoRecursoTest extends IntTestComum {

    @Autowired
    private PreInscricaoBuilder inscricaobuilder;

    @Autowired
    private PreInscricaoServico servico;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private InscricaoMapper mapper;


    @Test
    void criar() throws Exception {

        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isCreated());
    }

    @Test
    void listar() throws Exception{
        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        inscricaobuilder.buildDependencias(preInscricao);

        getMockMvc().perform(get("/api/inscricao")
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                    .andExpect(status().isOk());
    }

    @Test
    void atualizar() throws Exception{

        PreInscricao preInscricao = inscricaobuilder.construir();
        preInscricao.getSituacao().setId(2);

        getMockMvc().perform(put("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isOk());
    }

    @Test
    void deletar() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        servico.criar(mapper.toDto(preInscricao));

        getMockMvc().perform(delete("/api/inscricao/" + preInscricao.getId())
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))));

        getMockMvc().perform(delete("/api/inscricao/" + preInscricao.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isBadRequest());
    }


    @Test
    void obterPorIdComIdErrado() throws Exception {
        getMockMvc().perform(get("/api/inscricao/78"))
                    .andExpect(status().isBadRequest());
    }

    @Test
    void criarComEventoInvalidos() throws Exception {

        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        preInscricao.getEvento().setId(245);

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isBadRequest());


    }
    @Test
    void criarComUsuarioInvalido() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        preInscricao.getUsuario().setId(245);

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isBadRequest());
    }
    @Test
    void criarComSituacaoInvalida() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        preInscricao.getSituacao().setId(245);

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void inscricaoDuplicada() throws Exception {

        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaobuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isCreated());

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isBadRequest());

    }

}
