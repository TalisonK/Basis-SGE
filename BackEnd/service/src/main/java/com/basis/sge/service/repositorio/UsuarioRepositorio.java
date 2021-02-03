package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);

    Optional<Usuario> findByCpfAndChave(String cpf, String chave);

    Boolean existsByCpfAndIdNot(String cpf, Integer id);

}
