package com.basis.sge.service.recurso;

//métodos
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.basis.sge.service.servico.PerguntaServico;
import com.basis.sge.service.servico.dto.PerguntaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/pergunta")
@RequiredArgsConstructor

public class PerguntaRecurso {

    private final PerguntaServico perguntaServico;

    @GetMapping
    public ResponseEntity<List<PerguntaDTO>> listar() {
        return ResponseEntity.ok(perguntaServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaDTO> obterPorId(@PathVariable Integer id){
        return ResponseEntity.ok(perguntaServico.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PerguntaDTO> criar(@RequestBody PerguntaDTO perguntaDTO) {
        PerguntaDTO perguntaDtoCriado = perguntaServico.criar(perguntaDTO);
        return ResponseEntity.ok(perguntaDtoCriado);
    }

    @PutMapping
    public ResponseEntity<PerguntaDTO> atualizar(@RequestBody PerguntaDTO perguntadto) {
        PerguntaDTO perguntaDtoAtualizado = perguntaServico.atualizar(perguntadto);
        return ResponseEntity.ok(perguntaDtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        perguntaServico.deletar(id);
    }

}