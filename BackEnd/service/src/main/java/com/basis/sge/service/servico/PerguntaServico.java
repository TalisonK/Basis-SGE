package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.repositorio.PerguntaRepositorio;
import com.basis.sge.service.servico.dto.PerguntaDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.PerguntaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;  //corpo da requisição, envia dados pro servidor

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
        return perguntaMapper.toDto(listaPergunta);
    }

    public PerguntaDTO obterPorId(@RequestBody Integer id) {
        Pergunta pergunta = perguntaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Pergunta inexistente!"));
        return perguntaMapper.toDto(pergunta);
    }

    public PerguntaDTO criar(PerguntaDTO novaPergunta) {
        validaTitulo(novaPergunta.getTitulo());
        validaObrigatoriedade(novaPergunta.getObrigatoriedade());
        Pergunta pergunta = perguntaMapper.toEntity(novaPergunta);
        Pergunta perguntaCriada = perguntaRepositorio.save(pergunta);
        return perguntaMapper.toDto(perguntaCriada);
    }

    public PerguntaDTO atualizar(PerguntaDTO perguntaDTO) {
        validaTitulo(perguntaDTO.getTitulo());
        validaObrigatoriedade(perguntaDTO.getObrigatoriedade());
        Pergunta pergunta = perguntaRepositorio.findById(perguntaDTO.getId()).orElseThrow(() -> new RegraNegocioException("Pergunta não encontrada!"));
        Pergunta perguntaRecebida = perguntaMapper.toEntity(perguntaDTO);
        perguntaRecebida.setId (pergunta.getId());
        Pergunta perguntaAtualizada = perguntaRepositorio.save(pergunta);

        return perguntaMapper.toDto(perguntaAtualizada);
    }
    //verifica se um titulo é menor que 3 caracteres, em caso não nulo
    //verifica se o titulo já existe
    //verifica se o titulo é não nulo
    public void validaTitulo(String titulo){
        if (titulo!=null && titulo.length() < 3){
            throw new RegraNegocioException("Campo deve ter pelo menos 2 caracteres");
        }
        if (perguntaRepositorio.existsByTitulo(titulo)){
            throw new RegraNegocioException(("Pergunta já cadastrada!"));
        }
        if (titulo == null){
            throw new RegraNegocioException(("Título inválido!"));
        }
    }
    public void validaObrigatoriedade(Boolean obrigatoriedade) {
       if (obrigatoriedade == null) {
            throw new RegraNegocioException("Campo não pode ser nulo!");
       }
    }
    public void deletar(Integer id) {
        Pergunta pergunta = perguntaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Pergunta inexistente"));
        perguntaRepositorio.deleteById(id);
    }
}