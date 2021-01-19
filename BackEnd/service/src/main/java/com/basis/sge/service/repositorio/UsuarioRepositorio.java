package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Boolean existsByChave(String chave);

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);

    Usuario findByCpf(String cpf);

    Boolean existsByCpfAndIdNot(String cpf, Integer id);

}
