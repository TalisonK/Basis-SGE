package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.PreInscricaoBuilder;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class PreInscricaoRecurso extends IntTestComum {

    @Autowired
    private PreInscricaoBuilder builder = new PreInscricaoBuilder();

    @Autowired
    private InscricaoMapper mapper;

    @Test
    public void criar() throws Exception {

        PreInscricao preInscricao = builder.construirEntidade();
        builder.buildDependencias(preInscricao);

        getMockMvc().perform(post("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isCreated());

    }

    @Test
    public void listar() throws Exception{
        PreInscricao preInscricao = builder.construirEntidade();
        builder.buildDependencias(preInscricao);

        getMockMvc().perform(get("/api/inscricao")
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                    .andExpect(status().isOk());


    }

}
