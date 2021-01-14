package com.basis.sge.service.recurso;

import com.basis.sge.service.dominio.PreInscricao;
import com.sun.tools.javac.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inscricao")
public class InscricaoRecurso {

    @GetMapping
    public ResponseEntity<List<PreInscricao>> listar(){
        return ResponseEntity.ok();
    }
}
