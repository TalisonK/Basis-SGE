package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.Evento;
import com.basis.sge.service.dominio.PreInscricao;
import com.basis.sge.service.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepositorio extends JpaRepository<PreInscricao, Integer> {

    List<PreInscricao> findAllByEvento(Evento evento);

    List<PreInscricao> findAllByUsuarioId(Integer id);

    void deleteByUsuario(Usuario usuario);
}
