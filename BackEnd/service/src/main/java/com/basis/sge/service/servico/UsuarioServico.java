package com.basis.sge.service.servico;

import com.basis.sge.service.dominio.Usuario;
import com.basis.sge.service.repositorio.InscricaoRepositorio;
import com.basis.sge.service.repositorio.UsuarioRepositorio;
import com.basis.sge.service.servico.dto.UsuarioDTO;
import com.basis.sge.service.servico.exception.RegraNegocioException;
import com.basis.sge.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

    private final EmailServico emailServico;
    private final UsuarioRepositorio usuarioRepositorio;
    private final InscricaoRepositorio inscricaoRepositorio;
    private final UsuarioMapper usuarioMapper;
    

    public List<UsuarioDTO> listar() {
        return usuarioMapper.toDto(usuarioRepositorio.findAll());
    }

    public UsuarioDTO obterPorId(Integer id) {

        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {

        verificaUsuario(usuarioDTO);

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setChave(UUID.randomUUID().toString());
        Usuario usuarioCriado = usuarioRepositorio.save(usuario);

        emailServico.rabbitSendMail( usuarioDTO.getEmail(),
                "Cadastro efetuado com sucesso",
                "<body style=\"margin: 40; padding: 0;\">\n" +
                       " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                       "  <tr>\n" +
                       "   <td align=\"center\" bgcolor=\"#55DECE\" style=\"padding: 45x 0 25px 0; height: 200px\">\n" +
                       "      <img src=\"https://lh3.googleusercontent.com/fife/ABSRlIqwP-BJgfXU27hUF54lelPFy1xxKOepd6NBfvhpR_D5mhnEQXeE9NpSJXny0q2LCBO243YTv8ZsLBfBRdr1d51Qxx6rTFcBaMyAXBAz4bSbPnbbkra8NIbRZBzgOEbwzKwZYxySofo5qG8GYyK4bib0OZOW7-D3H6fZL20LvHfELAs4Vtj2-kB9vLF8Tp7cEM8Dqkv5VUSh5ZSrkAUh7zwOKxu5s4s_SI1-WGemsarUSifBAe6P_9Xh5w9teIuhomziR0VZyBNLlep0I48lbUcO0yvPYoc4koO1LngkW9c2VyiJMWACaNhJ1hIJmocEx7urYJ3QRg7TZPlsWQXeQe_qsUcrKapW0UkmYDaVgdkkMDx4jIIJirN55XGWkGPB9K8a3z4VO8tDb4pnVZvFJEUdKnX0QntbjD4_PVw-lTePh8ImUIh86PhnIfuB84D-55-0ZGQG_bdcw3gDDNjIGzX_QIghYWRxiEDyCz_M1g6mWlh5DEvd6P1NVVa-VlrFD8uHANl3OkX1JvXDGp9X8ARoCJ9LHn72wFQirMNeEuF9GUIg4DfJztKFBUFQC-kYeHzGFDS2hI6YufEnNKzoV-A7M-zf0FYVSElTOS5GxVcY6iUTp7E_tabzE-LWjFSMFZ95OEWfgAc_rO_oB_s0g1a9s8twbvDqGlxD4M4iqBfj4cmLLaOh_aZJ6KR2tIuWGk1deAdR-voSJzvhE_j87PGhFVHYenbAIA=w1920-h976-ft\" style=\"width: 50%;\"/>\n" +
                       "   </td>\n" +
                       "  </tr>\n" +
                       "  <tr>\n" +
                       "   <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"padding: 40px 0 30px 0;\">\n" +
                       "     <h2 style=\"font-size: 28px=; margin: auto; margin-top: 0%; margin-bottom: 0%;\">\n" +
                       "\tSistema de Gerenciamento de Eventos</h2>\n" +
                       "   </td>\n" +
                       "  </tr>\n" +
                       "  <tr>\n" +
                       "     <td align=\"center\" style=\"padding: 10px 0 10px 0; font-size: 22px\" />\n" +
                       "\tOlá Usuário(a)! É um prazer tê-lo(a) conosco. \n" +
                       "     </td>\n" +
                       "   </tr>\n" +
                       "   <tr>\n" +
                       "     <td align=\"center\" style=\"padding: 30px 0 20px 0; font-size: 22px\">\n" +
                       "\tO seu cadastro foi realizado pela BASIS Tecnologia. A partir de agora, você terá acesso à sua conta se conectando com as suas credenciais.\n" +
                       "     </td>\n" +
                       "    </tr>\n" +
                       "   </table>\n" +
                       "    </td>\n" +
                       "  </tr>\n" +
                       " </table>\n" +
                       "</body> "+ usuario.getChave(),
                new ArrayList<>());

        return usuarioMapper.toDto(usuarioCriado);
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDTO) {

        verificaUsuarioAtualizar(usuarioDTO);

        Usuario usuario = usuarioRepositorio.findById(usuarioDTO.getId()).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        Usuario usuarioRecebido = usuarioMapper.toEntity(usuarioDTO);
        usuarioRecebido.setChave(usuario.getChave());
        Usuario usuarioAtualizado = usuarioRepositorio.save(usuarioRecebido);

        return usuarioMapper.toDto(usuarioAtualizado);
    }

    public void deletar(Integer id) {

        inscricaoRepositorio.deleteByUsuario(usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuario não cadastrado!")));

        if(!usuarioRepositorio.existsById(id)) { throw new RegraNegocioException("Usuário inexistente");}

        usuarioRepositorio.deleteById(id);
    }

    public void verificaUsuario(UsuarioDTO usuarioDTO) {

        if (usuarioDTO == null) {
            throw new RegraNegocioException("Dados inválidos");
        }
        if (usuarioDTO.getNome() == null) {
            throw new RegraNegocioException("Existem campos a serem preenchidos");
        }
        if (usuarioRepositorio.existsByCpf(usuarioDTO.getCpf()).equals(true)) {
            throw new RegraNegocioException("CPF já cadastrado, tente novamente");
        }
        if (usuarioRepositorio.existsByEmail(usuarioDTO.getEmail()).equals(true)) {
            throw new RegraNegocioException("Email já cadastrado, tente novamente");
        }
    }

    public void verificaUsuarioAtualizar(UsuarioDTO usuarioDTO){

        if (usuarioRepositorio.existsByCpfAndIdNot(usuarioDTO.getCpf(), usuarioDTO.getId()).equals(true)){
            throw new RegraNegocioException("CPF já cadastrado");
        }
    }
}
