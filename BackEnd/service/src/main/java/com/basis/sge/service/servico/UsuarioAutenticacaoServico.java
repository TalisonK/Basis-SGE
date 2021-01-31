package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.UsuarioAutenticacaoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.UsuarioAutenticacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioAutenticacaoServico {

    private final UsuarioRepositorio usuarioRepositorio;

    private final UsuarioAutenticacaoMapper usuarioAutenticacaoMapper;

    public UsuarioAutenticacaoDTO autenticacaoCpfEChave(UsuarioAutenticacaoDTO usuarioAutenticacaoDTO) {
        Usuario usuario = usuarioRepositorio.findByCpfAndChave(usuarioAutenticacaoDTO.getCpf(), usuarioAutenticacaoDTO.getChave()).orElseThrow(() -> new RegraNegocioException("Autenticação negada"));
        return usuarioAutenticacaoMapper.toDto(usuario);
    }

}
