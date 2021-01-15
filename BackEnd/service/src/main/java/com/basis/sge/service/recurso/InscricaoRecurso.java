package com.basis.sge.service.recurso;
import com.basis.sge.service.servico.InscricaoServico;
import com.basis.sge.service.servico.dto.PreInscricaoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inscricao")
public class InscricaoRecurso {
    private InscricaoServico servico;

    @GetMapping
    public ResponseEntity<List<PreInscricaoDTO>> listar(){

        return ResponseEntity.ok(servico.listar());
    }
}
