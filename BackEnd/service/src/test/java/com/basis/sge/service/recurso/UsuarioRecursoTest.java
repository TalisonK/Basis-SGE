package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.EventoBuilder;
import com.basis.sge.service.builder.PreInscricaoBuilder;
import com.basis.sge.service.builder.UsuarioBuilder;
import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.PreInscricaoServico;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import com.basis.sge.service.util.IntTestComum;
import com.basis.sge.service.util.TestUtil;
import org.junit.jupiter.api.Assertions;
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
class UsuarioRecursoTest extends IntTestComum {

    @Autowired
    private UsuarioBuilder usuarioBuilder;


    @Autowired
    private PreInscricaoBuilder inscricaoBuilder;

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PreInscricaoServico preInscricaoServico;

    @Autowired
    private InscricaoMapper inscricaoMapper;
  

    @BeforeEach
    void inicializar() {
        //apagar repositorios.
        usuarioRepositorio.deleteAll();
    }

    @Test
    void listarTest() throws Exception {
        getMockMvc().perform(get("/api/usuarios"))
                .andExpect(status().isOk());
    }
  
    @Test
    void obterPorId() throws Exception {

        Usuario usuario = usuarioBuilder.construir();

        getMockMvc().perform(get("/api/usuarios/" + usuario.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void criarTest() throws Exception {

        Usuario usuario = usuarioBuilder.construirEntidade();

        getMockMvc().perform(post("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, usuarioRepositorio.findAll().size());
    }

    @Test
    void atualizarTest() throws Exception {

        Usuario usuario = usuarioBuilder.construir();

        usuario.setTelefone("8398898989");
        getMockMvc().perform(put("/api/usuarios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isOk());
    }

    @Test
    void deletarTest() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Evento evento = eventoBuilder.construir();

        PreInscricao preInscricao = inscricaoBuilder.construirEntidade();
        preInscricao.setUsuario(usuario);
        preInscricao.setEvento(evento);

        preInscricaoServico.criar(inscricaoMapper.toDto(preInscricao));

        getMockMvc().perform(delete("/api/usuarios/" + usuario.getId()))
                .andExpect(status().isOk());

        getMockMvc().perform(delete("/api/usuarios/" + usuario.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obterPorIdInexistente() throws Exception{

        Usuario usuario = usuarioBuilder.construir();

        int idUsuario = usuario.getId()+1;
        getMockMvc().perform(get("/api/usuarios/" + idUsuario))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletarIdInexistente()throws Exception{

        Usuario usuario = usuarioBuilder.construir();

        int idUsuario = usuario.getId()+1;
        getMockMvc().perform(delete("/api/usuarios/"+ idUsuario))
                .andExpect(status().isBadRequest());
    }

    @Test
    void criarUsuarioExistente()throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        verificaUsuario(usuario);
    }

    @Test
    void criarUsuarioNulo() throws Exception{
        verificaUsuario(null);
    }

    @Test
    void criarUsuarioNomeInvalido() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        usuario.setNome("");
        verificaUsuario(usuario);
    }
    @Test
    void criarUsuarioCpfExistente() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Usuario usuario2 = usuarioBuilder.construirEntidade();
        usuario2.setCpf(usuario.getCpf());
        verificaUsuario(usuario);
    }

    @Test
    void criarUsuarioEmailExistente() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Usuario usuario2 = usuarioBuilder.construirEntidade();
        usuario2.setEmail(usuario.getEmail());
        verificaUsuario(usuario);
    }

    @Test
    void atualizarUsuarioCpf() throws Exception{

        Usuario usuario = usuarioBuilder.construir();
        Usuario usuario2 = usuarioBuilder.construirEntidade();

        usuario2.setId(usuario.getId() + 2);
        usuario2.setTelefone("8399889888");
        verificaUsuarioPut(usuario2);
    }

    void verificaUsuario(Usuario usuario) throws Exception{

        getMockMvc().perform(post("/api/usuarios/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }
    void verificaUsuarioPut(Usuario usuario) throws Exception{

        getMockMvc().perform(put("/api/usuarios/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }
}
