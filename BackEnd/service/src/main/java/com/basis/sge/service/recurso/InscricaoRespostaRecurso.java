package com.basis.sge.service.recurso;

import com.basis.sge.service.dominio.IdInscricaoResposta;
import com.basis.sge.service.servico.InscricaoRespostaServico;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inscricaoResposta")
@RequiredArgsConstructor
public class InscricaoRespostaRecurso {

    private final InscricaoRespostaServico inscricaoRespostaServico;

    @GetMapping
    public ResponseEntity<List<InscricaoRespostaDTO>> listar(){
        return ResponseEntity.ok().body(inscricaoRespostaServico.listar());
    }

    @PostMapping
    public ResponseEntity<InscricaoRespostaDTO> criar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO){
        return ResponseEntity.status(201).body(inscricaoRespostaServico.criar(inscricaoRespostaDTO));
    }

    @PutMapping
    public ResponseEntity<InscricaoRespostaDTO> atualizar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO){
        return ResponseEntity.status(201).body(inscricaoRespostaServico.atualizar(inscricaoRespostaDTO));
    }

    @DeleteMapping
    public ResponseEntity<InscricaoRespostaDTO> deletar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO){
        return ResponseEntity.ok().body(inscricaoRespostaServico.deletar(inscricaoRespostaDTO));
    }
}