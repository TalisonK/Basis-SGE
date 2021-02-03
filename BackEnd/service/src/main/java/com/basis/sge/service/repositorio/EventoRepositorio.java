package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio  extends JpaRepository<Evento, Integer> {

    Boolean existsByTitulo(String titulo);

    Boolean existsByTituloAndIdNot (String titulo, Integer id);
}
