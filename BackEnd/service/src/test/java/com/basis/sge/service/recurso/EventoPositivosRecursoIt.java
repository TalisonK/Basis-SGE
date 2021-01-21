package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.EventoBuilder;
import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.mapper.EventoMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@Transactional
public class EventoPositivosRecursoIt extends IntTestComum{

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;


    @BeforeEach
    public void inicializar() {
        eventoRepositorio.deleteAll();
    }

    @Test
    public void listaTest() throws Exception {
        eventoBuilder.construir();
        getMockMvc().perform(get("/api/evento")).andExpect(status().isOk());
    }

    @Test
    public void criarTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();

        getMockMvc().perform(post("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
        Assert.assertEquals(1,eventoRepositorio.findAll().size());
    }

    @Test
    public void atualizarTest() throws Exception {

        Evento evento = eventoBuilder.construir();
        Integer idEvento = evento.getId();
        evento.setTitulo("Atualizado");


        getMockMvc().perform(put("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isOk());

    }

    @Test
    public void deletarTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEvento = evento.getId();
        getMockMvc().perform(delete("/api/evento/"+idEvento)).andExpect(status().isOk());
        Assert.assertEquals(0,eventoRepositorio.findAll().size());
    }

    public void ObterPorIdTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEvento = evento.getId();
        getMockMvc().perform(get("/api/evento/"+idEvento)).andExpect(status().isOk());

    }

    //=========================================================================================================



}