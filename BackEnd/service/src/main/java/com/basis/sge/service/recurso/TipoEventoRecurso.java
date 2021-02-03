package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.TipoEventoServico;
import com.basis.sge.service.servico.dto.TipoEventoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tipo-evento")
@RequiredArgsConstructor
public class TipoEventoRecurso {

    private final TipoEventoServico tipoEventoServico;


    @GetMapping
    public ResponseEntity<List<TipoEventoDTO>> listar(){
        return ResponseEntity.ok(tipoEventoServico.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEventoDTO> obterPorId(@PathVariable Integer id){
        return ResponseEntity.ok(tipoEventoServico.obterPorId(id));
    }

}
