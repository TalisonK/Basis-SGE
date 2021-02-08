package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.PerguntaBuilder;
import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.repositorio.PerguntaRepositorio;
import com.basis.sge.service.servico.mapper.PerguntaMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@Transactional
class PerguntaRecursoTest extends IntTestComum {

    @Autowired
    private PerguntaBuilder perguntaBuilder;

    @Autowired
    private PerguntaMapper perguntaMapper;

    @Autowired
    private PerguntaRepositorio perguntaRepositorio;

    @BeforeEach
    void inicializar(){
        perguntaRepositorio.deleteAll();
    }

    @Test
    void listarTest() throws Exception{
        perguntaBuilder.construir();
        getMockMvc().perform(get("/api/pergunta"))
                .andExpect(status().isOk());
    }

    @Test
    void criarTest() throws Exception{
        Pergunta pergunta = perguntaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isCreated());
    }

    @Test
    void atualizarTest() throws Exception{
        Pergunta pergunta = perguntaBuilder.construir();
        pergunta.setTitulo("Atualizado");
        getMockMvc().perform(put("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isOk());
    }

    @Test //TESTE PUT/ATUALIZAR NEGATIVO PARA TITULO INEXISTENTE
    void tituloExistenteTest() throws Exception{
        Pergunta pergunta = perguntaBuilder.construir();
        Pergunta pergunta2 = perguntaBuilder.construirEntidade();
        pergunta2.setTitulo("Pergunta?????");
        getMockMvc().perform(put("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isOk());
    }

    @Test //TESTE PUT/ATUALIZAR NEGATIVO PARA TITULO NAO NULO, MENOR QUE TRES CARACTERES
    void tituloNaoNuloMenorQueTres() throws Exception{
        Pergunta pergunta = perguntaBuilder.construir();
        pergunta.setTitulo("PE");
        getMockMvc().perform(put("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isBadRequest());
    }

    @Test //TESTE PUT/ATUALIZAR NEGATIVO PARA TITULO NULO
    void tituloNuloTest() throws  Exception{
        Pergunta pergunta = perguntaBuilder.construir();
        pergunta.setTitulo(null);
        getMockMvc().perform(put("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isBadRequest());
    }

    @Test //TESTE PUT/ATUALIZAR NEGATIVO DE OBRIGATORIEDADE NULA
    void obrigatoriedadeNulaTeste() throws  Exception{
        Pergunta pergunta = perguntaBuilder.construir();
        pergunta.setObrigatoriedade(null);
        getMockMvc().perform(put("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isBadRequest());
    }

    @Test //TESTE NEGATIVO ID DE PERGUNTA INEXISTENTE
    void perguntaIdExistente() throws Exception{
        Pergunta pergunta = perguntaBuilder.construirEntidade();
        pergunta.setId(25);
        getMockMvc().perform(put("/api/pergunta")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(perguntaMapper.toDto(pergunta))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletarTest() throws Exception {
        Pergunta pergunta = perguntaBuilder.construir();
        Integer idPergunta = pergunta.getId();
        getMockMvc().perform(delete("/api/pergunta/"+idPergunta))
                .andExpect(status().isOk());
        getMockMvc().perform(delete("/api/pergunta/"+idPergunta))
                .andExpect(status().isBadRequest());
    }

    @Test //TESTE NEGATIVO DELETE
    void deletarPerguntaInexistente() throws Exception {
        Pergunta pergunta = perguntaBuilder.construir();
        int idPergunta = pergunta.getId() + 1;
        getMockMvc().perform(delete("/api/pergunta/" +idPergunta))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ObterPorIdTest() throws Exception {
        Pergunta pergunta = perguntaBuilder.construir();
        Integer idPergunta = pergunta.getId();
        getMockMvc().perform(get("/api/pergunta/"+idPergunta))
                .andExpect(status().isOk());
    }
    @Test // TESTE NEGATIVO PARA GET OBTER POR ID INEXISTENTE
    void ObterPorIdInexistenteTest() throws Exception {
        Pergunta pergunta = perguntaBuilder.construir();
        int idPergunta = pergunta.getId() + 1;
        getMockMvc().perform(get("/api/pergunta/" +idPergunta))
                .andExpect(status().isBadRequest());
    }
}
