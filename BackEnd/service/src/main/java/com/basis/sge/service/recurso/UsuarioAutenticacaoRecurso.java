package com.basis.sge.service.recurso;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.servico.UsuarioAutenticacaoServico;
import com.basis.sge.service.servico.dto.UsuarioAutenticacaoDTO;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class UsuarioAutenticacaoRecurso {

    private final UsuarioAutenticacaoServico usuarioAutenticacaoServico;

    @PostMapping
    public ResponseEntity<UsuarioDTO> autenticacaoCpfEChave(@Valid @RequestBody UsuarioAutenticacaoDTO usuarioAutenticacaoDTO) throws URISyntaxException, RegraNegocioException {
        return ResponseEntity.ok(usuarioAutenticacaoServico.autenticacaoCpfEChave(usuarioAutenticacaoDTO));
    }
}
