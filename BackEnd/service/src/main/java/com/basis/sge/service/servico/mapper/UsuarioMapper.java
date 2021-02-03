package com.basis.sge.service.servico.mapper;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {


}