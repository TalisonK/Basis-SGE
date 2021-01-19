package com.basis.sge.service.repositorio;

import org.springframework.stereotype.Repository;
import com.basis.sge.service.dominio.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PerguntaRepositorio extends JpaRepository<Pergunta, Integer> {

    boolean existByTitle(String titulo);
}