package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.PreInscricaoServico;
import com.basis.sge.service.servico.dto.ConjuntoPerguntaRespostaDTO;
import com.basis.sge.service.servico.dto.InscricaoListagemDTO;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inscricao")
public class InscricaoRecurso {
    private final PreInscricaoServico servico;

    @GetMapping
    public ResponseEntity<List<InscricaoListagemDTO>> listar(){
        return ResponseEntity.ok(servico.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PreInscricaoDTO> obterPorId(@PathVariable Integer id){
        return ResponseEntity.ok().body(servico.obterPorId(id));
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<List<PreInscricaoDTO>> obterPorUsuarioId(@PathVariable Integer id){
        return ResponseEntity.ok().body(servico.obterPorUsuarioId(id));
    }

    @PostMapping(value = "/respostas")
    public ResponseEntity<List<ConjuntoPerguntaRespostaDTO>> obterRespostas(@RequestBody PreInscricaoDTO dto){
        return ResponseEntity.ok().body(servico.buscarPerguntasRespostas(dto));
    }

    @PostMapping
    public ResponseEntity<PreInscricaoDTO> criar(@RequestBody PreInscricaoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(dto));
    }

    @PutMapping
    public ResponseEntity<InscricaoListagemDTO> editar(@RequestBody PreInscricaoDTO dto){

        servico.idEmUso(dto.getId());

        return ResponseEntity.ok().body(servico.atualizar(dto));
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id){
        servico.deletar(id);
    }
}
