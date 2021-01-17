package com.basis.sge.service.recurso;

import com.basis.sge.service.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.service.servico.InscricaoRespostaServico;
import com.basis.sge.service.servico.UsuarioServico;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/inscricaoResposta")
@RequiredArgsConstructor
public class InscricaoRespostaRecurso {

    private final InscricaoRespostaServico inscricaoRespostaServico;

    @PostMapping
    public ResponseEntity<InscricaoRespostaDTO> criar(@Valid @RequestBody InscricaoRespostaDTO inscricaoRespostaDTO) throws URISyntaxException, RegraNegocioException {
        return ResponseEntity.created(new URI("/api/inscricaoResposta")).body(inscricaoRespostaServico.criar(inscricaoRespostaDTO));
    }
}
