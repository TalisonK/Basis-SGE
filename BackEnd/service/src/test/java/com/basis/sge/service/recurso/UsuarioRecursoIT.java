package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.UsuarioBuilder;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class UsuarioRecursoIT extends IntTestComum {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void inicializar() {
        usuarioRepositorio.deleteAll();
    }

    @Test
    public void listarTest() throws Exception {

        //usuarioBuilder.construir();
        getMockMvc().perform(get("/api/usuario"))
                .andExpect(status().isOk());
    }
    @Test
    public void obterPorId() throws Exception {

        Usuario usuario = usuarioBuilder.construir();
        getMockMvc().perform(get("/api/usuario/" + usuario.getId()))
                .andExpect(status().isOk());
    }

 
    @Test
    public void criarTest() throws Exception {

        Usuario usuario = usuarioBuilder.construirEntidade();
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isCreated());
        Assert.assertEquals(1, usuarioRepositorio.findAll().size());
    }

    @Test
    public void atualizarTest() throws Exception {
        Usuario usuario = usuarioBuilder.construir();

        usuario.setTelefone("8398898989");
        getMockMvc().perform(put("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarTest() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        getMockMvc().perform(delete("/api/usuario/"+usuario.getId())).andExpect(status().isOk());

        Assert.assertEquals(0, usuarioRepositorio.findAll().size());
    }


}