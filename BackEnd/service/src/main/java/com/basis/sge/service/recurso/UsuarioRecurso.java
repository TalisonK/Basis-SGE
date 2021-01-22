package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.UsuarioServico;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRecurso {
    private final UsuarioServico usuarioServico;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(){
        List<UsuarioDTO> lista = usuarioServico.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO>obterPorId(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioServico.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException, RegraNegocioException {
        return ResponseEntity.created(new URI("/api/usuarios")).body(usuarioServico.criar(usuarioDTO));
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuarioAtualizado = usuarioServico.atualizar(usuarioDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        usuarioServico.deletar(id);
    }

}
