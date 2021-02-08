package com.basis.sge.service.builder;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.UsuarioServico;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class UsuarioBuilder extends ConstrutorDeEntidade<Usuario> {

    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario construirEntidade() throws ParseException {

        Usuario usuario = new Usuario();

        usuario.setNome("José");
        usuario.setCpf("97816866003");
        usuario.setEmail("keninmonstro@gmail.com");
        usuario.setTelefone("83987672131");
        usuario.setDataNascimento(LocalDate.now());

        return usuario;
    }

    @Override
    public Usuario persistir(Usuario entidade) {

        UsuarioDTO usuarioDTO = usuarioServico.criar(usuarioMapper.toDto(entidade));
        return usuarioMapper.toEntity(usuarioDTO);
    }

    @Override
    public Collection<Usuario> obterTodos() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario obterPorId(Integer id) {

        return usuarioRepositorio.findById(id).orElse(null);
    }

}
