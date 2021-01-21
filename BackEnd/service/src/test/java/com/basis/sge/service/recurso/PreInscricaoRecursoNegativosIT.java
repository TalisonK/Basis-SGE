package com.basis.sge.service.recurso;

import com.basis.sge.service.builder.PreInscricaoBuilder;
import com.basis.sge.service.servico.PreInscricaoServico;
import com.basis.sge.service.servico.mapper.InscricaoMapper;
import com.basis.sge.service.util.IntTestComum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;


@ExtendWith(SpringExtension.class)
@Transactional
public class PreInscricaoRecursoNegativosIT extends IntTestComum {

    @Autowired
    private PreInscricaoBuilder builder = new PreInscricaoBuilder();

    @Autowired
    private PreInscricaoServico servico;

    @Autowired
    private InscricaoMapper mapper;

    @Test
    public void obterPorIdComIdErrado(){

    }
}
