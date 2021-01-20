package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.TipoSituacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSituacaoRepositorio extends JpaRepository<TipoSituacao,Integer> {
}
