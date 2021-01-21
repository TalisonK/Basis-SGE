package com.basis.sge.service.recurso;




import com.basis.sge.service.repositorio.TipoEventoRepositorio;

import com.basis.sge.service.util.IntTestComum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class TipoEventoRecursoIt extends IntTestComum {


    @Autowired
    private TipoEventoRepositorio tipoEventoRepositorio;


    @Test
    public void listaTest() throws Exception {
        getMockMvc().perform(get("/api/tipo-evento")).andExpect(status().isOk());
    }

}
