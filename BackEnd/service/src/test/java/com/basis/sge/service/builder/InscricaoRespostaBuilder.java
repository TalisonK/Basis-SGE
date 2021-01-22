package com.basis.sge.service.builder;


import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.dominio.InscricaoResposta;
import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.servico.InscricaoRespostaServico;
import com.basis.sge.service.servico.dto.InscricaoRespostaDTO;
import com.basis.sge.service.servico.mapper.InscricaoRespostaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Collection;

@Component
public class InscricaoRespostaBuilder extends ConstrutorDeEntidade<InscricaoResposta>{

    @Autowired
    private InscricaoRespostaServico inscricaoRespostaServico;

    @Autowired
    private PreInscricaoBuilder preInscricaoBuilder;

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private PerguntaBuilder perguntaBuilder;

    @Autowired
    private EventoBuilder eventoBuilder;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private InscricaoRespostaMapper inscricaoRespostaMapper;

    @Autowired
    private EventoPerguntaBuilder eventoPerguntaBuilder;


    @Override
    public InscricaoResposta construirEntidade() throws ParseException, Exception {

        Pergunta pergunta = perguntaBuilder.construirEntidade();
        pergunta.setTitulo("Outro Titulo");
        Pergunta perguntaSalva = perguntaBuilder.persistir(pergunta);

        Usuario usuario = usuarioBuilder.persistir(usuarioBuilder.construirEntidade());

        EventoPergunta eventoPergunta = eventoPerguntaBuilder.construirEntidade();
        eventoPergunta.setPergunta(perguntaSalva);
        Evento evento = eventoBuilder.construirEntidade();
        evento.getPerguntas().add(eventoPergunta);

        Evento eventoSalvo = eventoBuilder.persistir(evento);

        PreInscricao preInscricao = preInscricaoBuilder.construirEntidade();
        preInscricao.setEvento(eventoSalvo);
        preInscricao.setUsuario(usuario);

        PreInscricao inscricaoSalva = inscricaoRepositorio.save(preInscricao);

        InscricaoResposta inscricaoResposta = new InscricaoResposta();
        inscricaoResposta.setInscricao(inscricaoSalva);
        inscricaoResposta.setEvento(eventoSalvo);
        inscricaoResposta.setPergunta(perguntaSalva);
        inscricaoResposta.setResposta("Resposta");



        return inscricaoResposta;
    }

    @Override
    public InscricaoResposta persistir(InscricaoResposta entidade) {
        InscricaoRespostaDTO inscricaoRespostaDTO = inscricaoRespostaServico.criar(inscricaoRespostaMapper.toDto(entidade));
        return inscricaoRespostaMapper.toEntity(inscricaoRespostaDTO);
    }

    @Override
    protected Collection<InscricaoResposta> obterTodos() {
        return null;
    }

    @Override
    protected InscricaoResposta obterPorId(Integer id) {
        return null;
    }
}
