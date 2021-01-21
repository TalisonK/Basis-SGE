package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.PreInscricaoBuilder;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.servico.PreInscricaoServico;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@Transactional
public class PreInscricaoRecursoPositivosIT extends IntTestComum {

    @Autowired
    private PreInscricaoBuilder builder = new PreInscricaoBuilder();

    @Autowired
    private PreInscricaoServico servico;

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

    @Test
    public void atualizar() throws Exception{

        PreInscricao preInscricao = builder.construir();

        preInscricao.getSituacao().setId(2);

        getMockMvc().perform(put("/api/inscricao")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletar() throws Exception{

        PreInscricao preInscricao = builder.construir();


        getMockMvc().perform(delete("/api/inscricao/" + preInscricao.getId())
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))));

        getMockMvc().perform(delete("/api/inscricao/" + preInscricao.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mapper.toDto(preInscricao))))
                .andExpect(status().isBadRequest());
        ;
    }

}
