package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEventoRepositorio extends JpaRepository<TipoEvento,Integer> {
}
