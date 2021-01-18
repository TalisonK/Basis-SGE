package com.basis.sge.service.servico;



import com.basis.sge.service.dominio.TipoEvento;
import com.basis.sge.service.repositorio.TipoEventoRepositorio;

import com.basis.sge.service.servico.dto.TipoEventoDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.TipoEventoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TipoEventoServico {

    private final TipoEventoRepositorio tipoEventoRepositorio;

    private final TipoEventoMapper tipoEventoMapper;


    public List<TipoEventoDTO> listar() {
        List<TipoEvento> listaTipoEvento = tipoEventoRepositorio.findAll();

        return tipoEventoMapper.toDto(listaTipoEvento);
    }

    public TipoEventoDTO obterPorId(Integer id){
        TipoEvento tipoEvento = tipoEventoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de Evento não Existe no cadastro"));
        return tipoEventoMapper.toDto(tipoEvento);
    }

}
