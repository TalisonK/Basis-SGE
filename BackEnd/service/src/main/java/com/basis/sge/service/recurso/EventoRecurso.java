package com.basis.sge.service.recurso;


import com.basis.sge.service.servico.EventoServico;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
@RequiredArgsConstructor
public class EventoRecurso {

    private final EventoServico eventoServico;

    @GetMapping
    public List<UsuarioDTO> listar() {

        //return ResponseEntity.ok();
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obterPorId(@PathVariable Integer id){
        return null;
    }

    @PostMapping
    public UsuarioDTO criar(@RequestBody UsuarioDTO usuariodto) {
        return null;
    }
    @PutMapping
    public UsuarioDTO atualizar(@PathVariable Integer id) {

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

    }

}
