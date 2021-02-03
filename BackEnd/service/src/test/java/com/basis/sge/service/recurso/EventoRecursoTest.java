package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.EventoBuilder;
import com.basis.sge.service.builder.EventoPerguntaBuilder;
import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.repositorio.EventoPerguntaRepositorio;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.servico.mapper.EventoMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
class EventoRecursoTest extends IntTestComum{

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private EventoPerguntaBuilder eventoPerguntaBuilder;

    @Autowired
    private EventoPerguntaRepositorio eventoPerguntaRepositorio;

    @BeforeEach
    void inicializar() {
        eventoRepositorio.deleteAll();
        eventoPerguntaRepositorio.deleteAll();
    }

    @Test
    void listaTest() throws Exception {
        eventoBuilder.construir();
        getMockMvc().perform(get("/api/eventos")).andExpect(status().isOk());
    }

    @Test
    void criarTest() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();

        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1,eventoRepositorio.findAll().size());
    }

    @Test
    void atualizarTest() throws Exception {

        Evento evento = eventoBuilder.construir();
        evento.setTitulo("Atualizado");

        getMockMvc().perform(put("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isOk());

    }

    @Test
    void deletarTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEvento = evento.getId();
        getMockMvc().perform(delete("/api/eventos/"+idEvento)).andExpect(status().isOk());
        Assertions.assertEquals(0,eventoRepositorio.findAll().size());
    }

    @Test
    void obterPorIdTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEvento = evento.getId();
        getMockMvc().perform(get("/api/eventos/"+idEvento)).andExpect(status().isOk());

    }
    //=========================================================================================================
    //Teste de Evento com perguntas
    @Test
    void criarEventoComPerguntas() throws Exception{

        List<EventoPergunta> perguntaList = new ArrayList<>();
        EventoPergunta eventoPergunta = eventoPerguntaBuilder.construirEntidade();
        Evento evento = eventoBuilder.construirEntidade();

        eventoPergunta.setEvento(evento);
        perguntaList.add(eventoPergunta);
        evento.setPerguntas(perguntaList);

        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1,eventoRepositorio.findAll().size());
    }

    //=========================================================================================================
    //Testes para Bad Requests
    @Test
    void criarEventoTituloExistenteTest() throws Exception{
        Evento evento = eventoBuilder.construir();
        Evento eventoNovo = eventoBuilder.construirEntidade();
        eventoNovo.setTitulo(evento.getTitulo());
        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(eventoNovo))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarEventoTituloExistenteTest() throws Exception{
        Evento evento = eventoBuilder.persistir(eventoBuilder.construirEntidade());

        Evento eventoNovo = eventoBuilder.construirEntidade();
        eventoNovo.setTitulo("NovoEvento");

        Evento eventoAt = eventoBuilder.persistir(eventoNovo);
        eventoAt.setTitulo(evento.getTitulo());

        getMockMvc().perform(put("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(eventoAt))))
                .andExpect(status().isBadRequest());


    }


    @Test
    void criarEventoComNumerosNegativosTest() throws Exception{
        Evento evento = eventoBuilder.construirEntidade();
        evento.setValor(-10.0);
        evento.setQuantVagas(-1);
        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obterPorIdInvalidoTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        int idEventoInvalido = evento.getId() + 2;

        getMockMvc().perform(get("/api/eventos/"+idEventoInvalido)).andExpect(status().isBadRequest());

    }


    @Test
    void deletarIdInvalidoTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        int idEventoInvalido = evento.getId() + 2;

        getMockMvc().perform(delete("/api/eventos/"+idEventoInvalido)).andExpect(status().isBadRequest());

    }

    @Test
    void criarComTipoEventoInvalido() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.getTipoEvento().setId(9);
        getMockMvc().perform(post("/api/eventos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

}
