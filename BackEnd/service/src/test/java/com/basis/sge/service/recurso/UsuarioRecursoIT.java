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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
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
        getMockMvc().perform(get("/api/usuarios"))
                .andExpect(status().isOk());
    }
    @Test
    public void obterPorId() throws Exception {

        Usuario usuario = usuarioBuilder.construir();

        getMockMvc().perform(get("/api/usuarios/" + usuario.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void criarTest() throws Exception {

        Usuario usuario = usuarioBuilder.construirEntidade();

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isCreated());
        Assert.assertEquals(1, usuarioRepositorio.findAll().size());
    }

    @Test
    public void atualizarTest() throws Exception {

        Usuario usuario = usuarioBuilder.construir();

        usuario.setTelefone("8398898989");
        getMockMvc().perform(put("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarTest() throws Exception{

        Usuario usuario = usuarioBuilder.construir();

        getMockMvc().perform(delete("/api/usuarios/"+usuario.getId()))
                .andExpect(status().isOk());

        Assert.assertEquals(0, usuarioRepositorio.findAll().size());
    }

    @Test
    public void obterPorIdInexistente() throws Exception{

        Usuario usuario = usuarioBuilder.construir();

        Integer idUsuario = usuario.getId()+1;
        getMockMvc().perform(get("/api/usuarios/" + idUsuario))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletarIdInexistente()throws Exception{

        Usuario usuario = usuarioBuilder.construir();

        Integer idUsuario = usuario.getId()+1;
        getMockMvc().perform(delete("/api/usuarios/"+ idUsuario))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void criarUsuarioExistente()throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        verificaUsuario(usuario);
    }

    @Test
    public void criarUsuarioNulo() throws Exception{

        Usuario usuario = null;
        verificaUsuario(usuario);
    }

    @Test
    public void criarUsuarioNomeInvalido() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        usuario.setNome("");
        verificaUsuario(usuario);
    }
    @Test
    public void criarUsuarioCpfExistente() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Usuario usuario2 = usuarioBuilder.construirEntidade();
        usuario2.setCpf(usuario.getCpf());
        verificaUsuario(usuario);
    }

    @Test
    public void criarUsuarioEmailExistente() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Usuario usuario2 = usuarioBuilder.construirEntidade();
        usuario2.setEmail(usuario.getEmail());
        verificaUsuario(usuario);
    }

    @Test
    public void atualizarUsuarioCpf() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Usuario usuario2 = usuarioBuilder.construirEntidade();

        usuario2.setId(usuario.getId() + 2);
        usuario2.setTelefone("8399889888");
        verificaUsuarioPut(usuario2);
    }

    public void verificaUsuario(Usuario usuario) throws Exception{

        getMockMvc().perform(post("/api/usuarios/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }
    public void verificaUsuarioPut(Usuario usuario) throws Exception{

        getMockMvc().perform(put("/api/usuarios/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

}