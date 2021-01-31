package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.UsuarioAutenticacaoServico;
import com.basis.sge.service.servico.dto.UsuarioAutenticacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class UsuarioAutenticacaoRecurso {

    private final UsuarioAutenticacaoServico usuarioAutenticacaoServico;

    @GetMapping()
    public ResponseEntity<UsuarioAutenticacaoDTO> autenticacaoCpfEChave(@RequestBody UsuarioAutenticacaoDTO usuarioAutenticacaoDTO){

        return ResponseEntity.ok(usuarioAutenticacaoServico.autenticacaoCpfEChave(usuarioAutenticacaoDTO));
    }
}
