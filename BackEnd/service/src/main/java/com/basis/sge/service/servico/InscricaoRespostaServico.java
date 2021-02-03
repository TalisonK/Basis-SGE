package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.*;
import com.basis.sge.service.repositorio.EventoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.InscricaoRespostaRepositorio;
import com.basis.sge.service.repositorio.PerguntaRepositorio;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.InscricaoRespostaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class InscricaoRespostaServico {

    private final InscricaoRespostaRepositorio inscricaoRespostaRepositorio;
    private final EventoRepositorio eventoRepositorio;
    private final InscricaoRepositorio inscricaoRepositorio;
    private final PerguntaRepositorio perguntaRepositorio;
    private final InscricaoRespostaMapper inscricaoRespostaMapper;

    public List<InscricaoRespostaDTO> listar(){
        return inscricaoRespostaMapper.toDto(inscricaoRespostaRepositorio.findAll());
    }

    @GetMapping(value = "")
    public InscricaoRespostaDTO obterPorId(@RequestBody IdInscricaoResposta id) {

        if (id == null) {
            throw new RegraNegocioException("Dados inválidos");
        }

        return inscricaoRespostaMapper.toDto(inscricaoRespostaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Inscrição nao encontrada!")));
    }

    public InscricaoRespostaDTO criar(InscricaoRespostaDTO dto) {

        if (dto == null) {
            throw new RegraNegocioException("Dados inválidos");
        }

        if(!eventoRepositorio.existsById(dto.getIdEvento())){
            throw new RegraNegocioException("Evento não cadastrado");
        }

        InscricaoResposta ir = new InscricaoResposta();
        IdInscricaoResposta id = new IdInscricaoResposta(dto.getIdPergunta(),dto.getIdEvento(),dto.getIdInscricao());

        ir.setId(id);
        ir.setEvento(eventoRepositorio.findById(dto.getIdEvento()).orElseThrow(
                () -> new RegraNegocioException("Evento não cadastrado!")));

        ir.setInscricao(inscricaoRepositorio.findById(dto.getIdInscricao()).orElseThrow(
                () -> new RegraNegocioException("inscrição passada não existe!")));

        ir.setPergunta(perguntaRepositorio.findById(dto.getIdPergunta()).orElseThrow(
                () -> new RegraNegocioException("Pergunta não cadastrada")));

        ir.setResposta(dto.getResposta());

        InscricaoResposta inscricaoRespostaCriada = inscricaoRespostaRepositorio.save(ir);
        return inscricaoRespostaMapper.toDto(inscricaoRespostaCriada);
    }

    public InscricaoRespostaDTO obterPorId(Integer idInscricao,Integer idPergunta){
        Pergunta pergunta = perguntaRepositorio.findById(idPergunta).orElseThrow(() -> new RegraNegocioException("Pergunta não existe"));
        PreInscricao inscricao = inscricaoRepositorio.findById(idInscricao).orElseThrow(()->new RegraNegocioException("Inscricao não existe"));
        InscricaoResposta inscricaoResposta = inscricaoRespostaRepositorio.findByPerguntaAndInscricao(pergunta,inscricao);
        return inscricaoRespostaMapper.toDto(inscricaoResposta);
    }

    public void deletar(Integer idInscricao,Integer idPergunta) {
        Pergunta pergunta = perguntaRepositorio.findById(idPergunta).orElseThrow(() -> new RegraNegocioException("Pergunta não existe"));
        PreInscricao inscricao = inscricaoRepositorio.findById(idInscricao).orElseThrow(()->new RegraNegocioException("Inscricao não existe"));
        inscricaoRespostaRepositorio.deleteByPerguntaAndInscricao(pergunta,inscricao);
    }

}