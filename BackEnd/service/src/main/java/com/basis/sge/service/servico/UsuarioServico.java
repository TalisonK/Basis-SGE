package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
// import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> listar() {
        List lista = usuarioRepositorio.findAll();
        return usuarioMapper.toDto(lista);
    }
    public UsuarioDTO obterPorId(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return usuarioMapper.toDto(usuario);
    }
    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        /*
        if(usuarioDTO.getId() == null){

        }
        //criar gerador de chave aleatorio
*/
        usuario.setChave("132345");
        Usuario usuarioCriado = usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuarioCriado);
    }
    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioAtualizado = usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuarioAtualizado);
    }
    public void deletar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }
}
