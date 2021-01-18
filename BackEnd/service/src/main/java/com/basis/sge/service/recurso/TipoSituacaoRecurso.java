package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.TipoSituacaoServico;
import com.basis.sge.service.servico.dto.TipoSituacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/situacao")
public class TipoSituacaoRecurso {

    private final TipoSituacaoServico servico;


    @GetMapping
    public ResponseEntity<List<TipoSituacaoDTO>> listar(){
        return ResponseEntity.ok(servico.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoSituacaoDTO> obterPorID(@PathVariable Integer id){
        return ResponseEntity.ok(servico.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoSituacaoDTO> criar(@RequestBody TipoSituacaoDTO dto){
        return ResponseEntity.status(201).body(servico.criar(dto));
    }

    @PutMapping
    public ResponseEntity<TipoSituacaoDTO> editar(@RequestBody TipoSituacaoDTO dto){
        return ResponseEntity.ok().body(servico.atualizar(dto));
    }

    @DeleteMapping
    public ResponseEntity<TipoSituacaoDTO> deletar(@RequestBody TipoSituacaoDTO dto){
        return ResponseEntity.ok().body(servico.deletar(dto));
    }

}
