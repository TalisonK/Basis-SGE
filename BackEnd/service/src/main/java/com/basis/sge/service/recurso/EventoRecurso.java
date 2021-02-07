package com.basis.sge.service.recurso;

import com.basis.sge.service.servico.EventoServico;
import com.basis.sge.service.servico.dto.EventoDTO;
import com.basis.sge.service.servico.dto.EventoListagemDTO;
import com.basis.sge.service.servico.dto.PerguntaDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/eventos")
@AllArgsConstructor
public class EventoRecurso {

    private final EventoServico eventoServico;


    @GetMapping("/{id}/perguntas")
    public ResponseEntity<List<PerguntaDTO>> listarPerguntasEvento(@PathVariable Integer id){
        return ResponseEntity.ok(eventoServico.listarPerguntaEvento(id));
    }

    @GetMapping
    public ResponseEntity<List<EventoListagemDTO>> listar() {
        return ResponseEntity.ok(eventoServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obterPorId(@PathVariable Integer id){

        return ResponseEntity.ok(eventoServico.obterPorId(id));

    }

    @PostMapping
    public ResponseEntity<EventoDTO> criar(@RequestBody @Valid EventoDTO eventodto) {
        EventoDTO eventoDtoCriado = eventoServico.criar(eventodto);
        return ResponseEntity.created(URI.create("/api/eventos")).body(eventoDtoCriado);
    }

    @PutMapping
    public ResponseEntity<EventoDTO> atualizar(@RequestBody @Valid EventoDTO eventodto) {
        EventoDTO eventoDtoAtualizado = eventoServico.atualizar(eventodto);
        return ResponseEntity.ok(eventoDtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        eventoServico.deletar(id);
    }

}
