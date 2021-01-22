package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.EventoPergunta;
import com.basis.sge.service.dominio.IdEventoPergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoPerguntaRepositorio extends JpaRepository<EventoPergunta, IdEventoPergunta> {


}
