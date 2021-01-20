package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.IdInscricaoResposta;
import com.basis.sge.service.dominio.InscricaoResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRespostaRepositorio extends JpaRepository<InscricaoResposta, IdInscricaoResposta> {

}
