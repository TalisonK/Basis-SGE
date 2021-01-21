package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.EmailDTO;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;

    private final UsuarioMapper usuarioMapper;

    private final EmailServico emailServico;

    public List<UsuarioDTO> listar() {
        List lista = usuarioRepositorio.findAll();
        return usuarioMapper.toDto(lista);
    }

    public UsuarioDTO obterPorId(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        verificaUsuario(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setChave(UUID.randomUUID().toString());
        Usuario usuarioCriado = usuarioRepositorio.save(usuario);

        emailServico.sendMail( new EmailDTO(
                usuarioDTO.getEmail(),
                "Seu cadastro foi feito, sua chave é: "+ usuario.getChave(),
                "Cadastro efetuado com sucesso"
        ));
        return usuarioMapper.toDto(usuarioCriado);
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {
        verificaUsuarioAtualizar(usuarioDTO);
        Usuario usuario = usuarioRepositorio.findById(usuarioDTO.getId()).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Usuario usuarioRecebido = usuarioMapper.toEntity(usuarioDTO);
        usuarioRecebido.setChave(usuario.getChave());
        Usuario usuarioAtualizado = usuarioRepositorio.save(usuarioRecebido);

        return usuarioMapper.toDto(usuarioAtualizado);
    }

    public void deletar(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuário inexistente"));
        usuarioRepositorio.deleteById(id);
    }

    public void verificaUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            throw new RegraNegocioException("Dados inválidos");
        }
        if (usuarioDTO.getNome() == null) {
            throw new RegraNegocioException("Existem campos a serem preenchidos");
        }
        if (usuarioRepositorio.existsByCpf(usuarioDTO.getCpf())) {
            throw new RegraNegocioException("CPF já cadastrado, tente novamente");
        }
        if (usuarioRepositorio.existsByEmail(usuarioDTO.getEmail())) {
            throw new RegraNegocioException("Email já cadastrado, tente novamente");
        }
    }

    public void verificaUsuarioAtualizar(UsuarioDTO usuarioDTO){
        if (usuarioRepositorio.existsByCpfAndIdNot(usuarioDTO.getCpf(), usuarioDTO.getId())){
            throw new RegraNegocioException("CPF já cadastrado");
        }
    }
}
