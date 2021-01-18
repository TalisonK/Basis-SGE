package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.InscricaoRespostaServico;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/inscricaoResposta")
@RequiredArgsConstructor
public class InscricaoRespostaRecurso {

    private final InscricaoRespostaServico inscricaoRespostaServico;

    @GetMapping
    public ResponseEntity<List<InscricaoRespostaDTO>> listar() {
        return ResponseEntity.ok().body(inscricaoRespostaServico.listar());
    }

    @PostMapping
    public ResponseEntity<InscricaoRespostaDTO> criar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO) {
        return ResponseEntity.status(201).body(inscricaoRespostaServico.criar(inscricaoRespostaDTO));
    }

    @PutMapping
    public ResponseEntity<InscricaoRespostaDTO> atualizar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO) {
        return ResponseEntity.status(201).body(inscricaoRespostaServico.atualizar(inscricaoRespostaDTO));
    }

    @DeleteMapping
    public ResponseEntity<InscricaoRespostaDTO> deletar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO) {
        return ResponseEntity.ok().body(inscricaoRespostaServico.deletar(inscricaoRespostaDTO));
    }
}