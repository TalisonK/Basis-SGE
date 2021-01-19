package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepositorio  extends JpaRepository<Pergunta,Integer> {
}
