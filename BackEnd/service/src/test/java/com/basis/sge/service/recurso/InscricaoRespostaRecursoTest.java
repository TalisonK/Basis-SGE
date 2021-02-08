package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.InscricaoRespostaBuilder;
import com.basis.sge.service.dominio.InscricaoResposta;
import com.basis.sge.service.repositorio.*;
import com.basis.sge.service.servico.mapper.InscricaoRespostaMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
class InscricaoRespostaRecursoTest extends IntTestComum {

    @Autowired
    private InscricaoRespostaBuilder inscricaoRespostaBuilder;

    @Autowired
    private InscricaoRespostaRepositorio inscricaoRespostaRepositorio;

    @Autowired
    private InscricaoRespostaMapper inscricaoRespostaMapper;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PerguntaRepositorio perguntaRepositorio;

    @Autowired
    private EventoPerguntaRepositorio eventoPerguntaRepositorio;

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @BeforeEach
    void inicializar() {
        inscricaoRespostaRepositorio.deleteAll();
        eventoPerguntaRepositorio.deleteAll();
        perguntaRepositorio.deleteAll();
        eventoRepositorio.deleteAll();
        usuarioRepositorio.deleteAll();
        inscricaoRepositorio.deleteAll();

    }


    @Test
    void listaTest() throws Exception {

        getMockMvc().perform(get("/api/inscricaoResposta")).andExpect(status().isOk());
    }

    @Test
    void criarTest() throws Exception {
        InscricaoResposta inscricaoResposta= inscricaoRespostaBuilder.construirEntidade();

        getMockMvc().perform(post("/api/inscricaoResposta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(inscricaoRespostaMapper.toDto(inscricaoResposta))))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1,inscricaoRespostaRepositorio.findAll().size());
    }

    @Test
    void obterPorIdTest() throws Exception {
        InscricaoResposta inscricaoResposta = inscricaoRespostaBuilder.construir();
        Integer idInscricao = inscricaoResposta.getInscricao().getId();
        Integer idPergunta = inscricaoResposta.getPergunta().getId();
        getMockMvc().perform(get("/api/inscricaoResposta/"+idInscricao+"/"+idPergunta))
                .andExpect(status().isOk());

    }
    @Test
    void deletePorIdTest() throws Exception {
        InscricaoResposta inscricaoResposta = inscricaoRespostaBuilder.construir();
        Integer idInscricao = inscricaoResposta.getInscricao().getId();
        Integer idPergunta = inscricaoResposta.getPergunta().getId();
        getMockMvc().perform(delete("/api/inscricaoResposta/"+idInscricao+"/"+idPergunta))
                .andExpect(status().isOk());
        Assertions.assertEquals(0,inscricaoRespostaRepositorio.findAll().size());
    }

}
