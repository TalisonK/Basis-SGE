package com.basis.sge.service.recurso;
import com.basis.sge.service.servico.InscricaoServico;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inscricao")
public class InscricaoRecurso {
    private final InscricaoServico servico;

    @GetMapping
    public ResponseEntity<List<PreInscricaoDTO>> listar(){
        return ResponseEntity.ok(servico.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PreInscricaoDTO> obterPorID(@PathVariable Integer id){
        return ResponseEntity.ok(servico.buscar(id));
    }

    @PostMapping
    public ResponseEntity<PreInscricaoDTO> criar(@RequestBody PreInscricaoDTO dto){
        return ResponseEntity.status(201).body(servico.criar(dto));
    }


}
