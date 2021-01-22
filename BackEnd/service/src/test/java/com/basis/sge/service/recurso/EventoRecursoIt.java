package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.EventoBuilder;
import com.basis.sge.service.builder.EventoPerguntaBuilder;
import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.EventoPergunta;
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
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@ExtendWith(SpringExtension.class)
@Transactional
public class EventoRecursoIt extends IntTestComum{

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private EventoPerguntaBuilder eventoPerguntaBuilder;


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

    @Test
    public void obterPorIdTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEvento = evento.getId();
        getMockMvc().perform(get("/api/evento/"+idEvento)).andExpect(status().isOk());

    }
    //=========================================================================================================
    //Teste de Evento com perguntas
    @Test
    public void criarEventoComPerguntas() throws Exception{

        List<EventoPergunta> perguntaList = new ArrayList<>();
        EventoPergunta eventoPergunta = eventoPerguntaBuilder.construirEntidade();
        Evento evento = eventoBuilder.construirEntidade();

        eventoPergunta.setEvento(evento);
        perguntaList.add(eventoPergunta);
        evento.setPerguntas(perguntaList);

        getMockMvc().perform(post("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isCreated());
        Assert.assertEquals(1,eventoRepositorio.findAll().size());
    }

    //=========================================================================================================
    //Testes para Bad Requests
    @Test
    public void criarEventoTituloExistenteTest() throws Exception{
        Evento evento = eventoBuilder.construir();
        Evento eventoNovo = eventoBuilder.construirEntidade();
        eventoNovo.setTitulo(evento.getTitulo());
        getMockMvc().perform(post("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(eventoNovo))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void atualizarEventoTituloExistenteTest() throws Exception{
        Evento evento = eventoBuilder.persistir(eventoBuilder.construirEntidade());

        Evento eventoNovo = eventoBuilder.construirEntidade();
        eventoNovo.setTitulo("NovoEvento");

        Evento eventoAt = eventoBuilder.persistir(eventoNovo);
        eventoAt.setTitulo(evento.getTitulo());

        getMockMvc().perform(put("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(eventoAt))))
                .andExpect(status().isBadRequest());


    }


    @Test
    public void criarEventoComNumerosNegativosTest() throws Exception{
        Evento evento = eventoBuilder.construirEntidade();
        evento.setValor(-10.0);
        evento.setQuantVagas(-1);
        getMockMvc().perform(post("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void obterPorIdInvalidoTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEventoInvalido = evento.getId() + 2;

        getMockMvc().perform(get("/api/evento/"+idEventoInvalido)).andExpect(status().isBadRequest());

    }


    @Test
    public void deletarIdInvalidoTest() throws Exception {
        Evento evento = eventoBuilder.construir();
        Integer idEventoInvalido = evento.getId() + 2;

        getMockMvc().perform(delete("/api/evento/"+idEventoInvalido)).andExpect(status().isBadRequest());

    }

    @Test
    public void criarComTipoEventoInvalido() throws Exception {
        Evento evento = eventoBuilder.construirEntidade();
        evento.getTipoEvento().setId(9);
        getMockMvc().perform(post("/api/evento")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(eventoMapper.toDto(evento))))
                .andExpect(status().isBadRequest());
    }

}
