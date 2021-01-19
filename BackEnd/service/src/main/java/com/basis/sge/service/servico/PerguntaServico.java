package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.repositorio.PerguntaRepositorio;
import com.basis.sge.service.servico.dto.PerguntaDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.PerguntaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PerguntaServico {

    private final PerguntaRepositorio perguntaRepositorio;
    private final PerguntaMapper perguntaMapper;

    public List<PerguntaDTO> listar() {
        List<Pergunta> listaPergunta = perguntaRepositorio.findAll();
        return perguntaMapper.toDto(lista);
    }
    public PerguntaDTO obterPorId(Integer id) {
        Pergunta pergunta = perguntaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Pergunta inexistente!"));

        return perguntaMapper.toDto(pergunta);
    }
    public PerguntaDTO criar(PerguntaDTO novaPergunta) {
        if (perguntaRepositorio.existByTitle(novaPergunta.getTitulo())){
            throw new RegraNegocioException("Pergunta já cadastrada!");

        Pergunta pergunta = perguntaMapper.toEntity(novaPergunta);
        Pergunta perguntaCriada = perguntaRepositorio.save(pergunta);

        return perguntaMapper.toDto(perguntaCriada);
    }
    public PerguntaDTO atualizar(PerguntaDTO perguntaDTO) {
        Pergunta pergunta = perguntaMapper.toEntity(perguntaDTO);
        Pergunta perguntaAtualizada = perguntaRepositorio.save(pergunta);

        return perguntaMapper.toDto(perguntaAtualizada);
    }
    public void deletar(Integer id) {
        perguntaRepositorio.deleteById(id);
    }
}