package com.basis.sge.service.builder;

import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.dominio.Pergunta;
import com.basis.sge.service.repositorio.EventoPerguntaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EventoPerguntaBuilder extends ConstrutorDeEntidade<EventoPergunta>{

    @Autowired
    private EventoPerguntaRepositorio eventoPerguntaRepositorio;

    @Autowired
    private PerguntaBuilder perguntaBuilder;


    @Override
    public EventoPergunta construirEntidade() throws Exception
    {
        EventoPergunta eventoPergunta = new EventoPergunta();

        Pergunta primeiraPergunta =perguntaBuilder.persistir(perguntaBuilder.construirEntidade());

        eventoPergunta.setPergunta(primeiraPergunta);

        return eventoPergunta;
    }

    @Override
    public EventoPergunta persistir(EventoPergunta entidade)
    {
        return eventoPerguntaRepositorio.save(entidade);
    }

    @Override
    public List<EventoPergunta> obterTodos()
    {
        return eventoPerguntaRepositorio.findAll();
    }

    @Override
    public EventoPergunta obterPorId(Integer id)
    {
        return null;
    }


}


