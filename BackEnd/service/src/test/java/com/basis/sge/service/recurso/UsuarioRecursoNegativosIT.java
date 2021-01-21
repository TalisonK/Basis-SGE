package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.UsuarioBuilder;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
public class UsuarioRecursoNegativosIT extends IntTestComum {

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
    public void obterPorIdInexiste() throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        Integer idUsuario = usuario.getId()+1;
        getMockMvc().perform(get("/api/usuario/" + idUsuario))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletarIdInexistente()throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        Integer idUsuario = usuario.getId()+1;
        getMockMvc().perform(delete("/api/usuario/"+ idUsuario))
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
        usuario2.setCpf("05260961013");
        usuario2.setEmail("thayanenns@gmail.com");
        verificaUsuarioPut(usuario2);
    }

    public void verificaUsuario(Usuario usuario) throws Exception{
        getMockMvc().perform(post("/api/usuario/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }
    public void verificaUsuarioPut(Usuario usuario) throws Exception{
        getMockMvc().perform(put("/api/usuario/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }
}
