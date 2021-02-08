package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.InscricaoRespostaServico;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @DeleteMapping(value="/{idInscricao}/{idPergunta}")
    public void deletar(@PathVariable Integer idInscricao, @PathVariable Integer idPergunta) {

        inscricaoRespostaServico.deletar(idInscricao,idPergunta);

    }

    @GetMapping(value="/{idInscricao}/{idPergunta}")
    public ResponseEntity<InscricaoRespostaDTO> obterPorId(@PathVariable Integer idInscricao, @PathVariable Integer idPergunta) {

        return ResponseEntity.ok(inscricaoRespostaServico.obterPorId(idInscricao,idPergunta));

    }

    @PostMapping
    public ResponseEntity<InscricaoRespostaDTO> criar(@RequestBody InscricaoRespostaDTO inscricaoRespostaDTO){
        return ResponseEntity.status(201).body(inscricaoRespostaServico.criar(inscricaoRespostaDTO));
    }




}