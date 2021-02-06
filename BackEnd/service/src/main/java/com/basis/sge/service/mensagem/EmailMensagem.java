package com.basis.sge.service.mensagem;

public class EmailMensagem {
    public String messageUsuarioCriado(String nome, String chave){
        return "<body style=\"margin: 40; padding: 0;\">\n" +
                " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#55DECE\" style=\"padding: 45x 0 25px 0; height: 200px\">\n" +
                "      <img src=\"https://lh3.googleusercontent.com/fife/ABSRlIqwP-BJgfXU27hUF54lelPFy1xxKOepd6NBfvhpR_D5mhnEQXeE9NpSJXny0q2LCBO243YTv8ZsLBfBRdr1d51Qxx6rTFcBaMyAXBAz4bSbPnbbkra8NIbRZBzgOEbwzKwZYxySofo5qG8GYyK4bib0OZOW7-D3H6fZL20LvHfELAs4Vtj2-kB9vLF8Tp7cEM8Dqkv5VUSh5ZSrkAUh7zwOKxu5s4s_SI1-WGemsarUSifBAe6P_9Xh5w9teIuhomziR0VZyBNLlep0I48lbUcO0yvPYoc4koO1LngkW9c2VyiJMWACaNhJ1hIJmocEx7urYJ3QRg7TZPlsWQXeQe_qsUcrKapW0UkmYDaVgdkkMDx4jIIJirN55XGWkGPB9K8a3z4VO8tDb4pnVZvFJEUdKnX0QntbjD4_PVw-lTePh8ImUIh86PhnIfuB84D-55-0ZGQG_bdcw3gDDNjIGzX_QIghYWRxiEDyCz_M1g6mWlh5DEvd6P1NVVa-VlrFD8uHANl3OkX1JvXDGp9X8ARoCJ9LHn72wFQirMNeEuF9GUIg4DfJztKFBUFQC-kYeHzGFDS2hI6YufEnNKzoV-A7M-zf0FYVSElTOS5GxVcY6iUTp7E_tabzE-LWjFSMFZ95OEWfgAc_rO_oB_s0g1a9s8twbvDqGlxD4M4iqBfj4cmLLaOh_aZJ6KR2tIuWGk1deAdR-voSJzvhE_j87PGhFVHYenbAIA=w1920-h976-ft\" style=\"width: 50%;\"/>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"padding: 40px 0 30px 0;\">\n" +
                "     <h2 style=\"font-size: 28px; margin: auto; margin-top: 0%; margin-bottom: 0%;\">\n" +
                "\tSistema de Gerenciamento de Eventos</h2>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "     <td align=\"center\" style=\"padding: 10px 0 10px 0; font-size: 22px\" />\n" +
                "\tOlá "+nome+"! É um prazer tê-lo(a) conosco. A sua CHAVE de acesso é "+chave+"\n" +
                "     </td>\n" +
                "   </tr>\n" +
                "   <tr>\n" +
                "     <td align=\"center\" style=\"padding: 30px 0 20px 0; font-size: 22px\">\n" +
                "\tO seu cadastro foi realizado pela BASIS Tecnologia. A partir de agora, você terá acesso à sua conta se conectando com as suas credenciais.\n" +
                "     </td>\n" +
                "  </tr>\n" +
                " </table>\n" +
                "</body>";
    }
    public String messageEventoEditado(String nome,String evento){
        return "<body style=\"margin: 25; padding: 25;\">\n" +
                " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#55DECE\" style=\"padding: 45x 0 25px 0; height: 200px\">\n" +
                "      <img src=\"https://lh3.googleusercontent.com/fife/ABSRlIqwP-BJgfXU27hUF54lelPFy1xxKOepd6NBfvhpR_D5mhnEQXeE9NpSJXny0q2LCBO243YTv8ZsLBfBRdr1d51Qxx6rTFcBaMyAXBAz4bSbPnbbkra8NIbRZBzgOEbwzKwZYxySofo5qG8GYyK4bib0OZOW7-D3H6fZL20LvHfELAs4Vtj2-kB9vLF8Tp7cEM8Dqkv5VUSh5ZSrkAUh7zwOKxu5s4s_SI1-WGemsarUSifBAe6P_9Xh5w9teIuhomziR0VZyBNLlep0I48lbUcO0yvPYoc4koO1LngkW9c2VyiJMWACaNhJ1hIJmocEx7urYJ3QRg7TZPlsWQXeQe_qsUcrKapW0UkmYDaVgdkkMDx4jIIJirN55XGWkGPB9K8a3z4VO8tDb4pnVZvFJEUdKnX0QntbjD4_PVw-lTePh8ImUIh86PhnIfuB84D-55-0ZGQG_bdcw3gDDNjIGzX_QIghYWRxiEDyCz_M1g6mWlh5DEvd6P1NVVa-VlrFD8uHANl3OkX1JvXDGp9X8ARoCJ9LHn72wFQirMNeEuF9GUIg4DfJztKFBUFQC-kYeHzGFDS2hI6YufEnNKzoV-A7M-zf0FYVSElTOS5GxVcY6iUTp7E_tabzE-LWjFSMFZ95OEWfgAc_rO_oB_s0g1a9s8twbvDqGlxD4M4iqBfj4cmLLaOh_aZJ6KR2tIuWGk1deAdR-voSJzvhE_j87PGhFVHYenbAIA=w1920-h976-ft\" style=\"width: 50%;\"/>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"padding: 40px 0 30px 0;\">\n" +
                "     <h2 style=\"font-size: 28px=; margin: auto; margin-top: 0%; margin-bottom: 0%;\">\n" +
                "\tSistema de Gerenciamento de Eventos\n" +
                "     </h2>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "     <td align=\"center\" style=\"padding: 10px 0 10px 0; font-size: 22px\"/>\n" +
                "\tOlá "+nome+"!\n" +
                "     </td>\n" +
                "   </tr>\n" +
                "   <tr>\n" +
                "     <td align=\"center\" style=\"padding: 30px 0 20px 0; font-size: 22px\">\n" +
                "\tO evento: "+evento +" que você está inscrito foi editado.\n" +
                "     </td>\n" +
                "    </tr>\n" +
                "   </table>\n" +
                "</body>";
    }
    public String messageEventoCancelado(String nome,String evento){
        return "<body style=\"margin: 25; padding: 25;\">\n" +
                " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#55DECE\" style=\"padding: 45x 0 25px 0; height: 200px\">\n" +
                "      <img src=\"https://lh3.googleusercontent.com/fife/ABSRlIqwP-BJgfXU27hUF54lelPFy1xxKOepd6NBfvhpR_D5mhnEQXeE9NpSJXny0q2LCBO243YTv8ZsLBfBRdr1d51Qxx6rTFcBaMyAXBAz4bSbPnbbkra8NIbRZBzgOEbwzKwZYxySofo5qG8GYyK4bib0OZOW7-D3H6fZL20LvHfELAs4Vtj2-kB9vLF8Tp7cEM8Dqkv5VUSh5ZSrkAUh7zwOKxu5s4s_SI1-WGemsarUSifBAe6P_9Xh5w9teIuhomziR0VZyBNLlep0I48lbUcO0yvPYoc4koO1LngkW9c2VyiJMWACaNhJ1hIJmocEx7urYJ3QRg7TZPlsWQXeQe_qsUcrKapW0UkmYDaVgdkkMDx4jIIJirN55XGWkGPB9K8a3z4VO8tDb4pnVZvFJEUdKnX0QntbjD4_PVw-lTePh8ImUIh86PhnIfuB84D-55-0ZGQG_bdcw3gDDNjIGzX_QIghYWRxiEDyCz_M1g6mWlh5DEvd6P1NVVa-VlrFD8uHANl3OkX1JvXDGp9X8ARoCJ9LHn72wFQirMNeEuF9GUIg4DfJztKFBUFQC-kYeHzGFDS2hI6YufEnNKzoV-A7M-zf0FYVSElTOS5GxVcY6iUTp7E_tabzE-LWjFSMFZ95OEWfgAc_rO_oB_s0g1a9s8twbvDqGlxD4M4iqBfj4cmLLaOh_aZJ6KR2tIuWGk1deAdR-voSJzvhE_j87PGhFVHYenbAIA=w1920-h976-ft\" style=\"width: 50%;\"/>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"padding: 40px 0 30px 0;\">\n" +
                "     <h2 style=\"font-size: 28px=; margin: auto; margin-top: 0%; margin-bottom: 0%;\">\n" +
                "\tSistema de Gerenciamento de Eventos\n" +
                "     </h2>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "     <td align=\"center\" style=\"padding: 10px 0 10px 0; font-size: 22px\"/>\n" +
                "\tOlá "+nome+"!\n" +
                "     </td>\n" +
                "   </tr>\n" +
                "   <tr>\n" +
                "     <td align=\"center\" style=\"padding: 30px 0 20px 0; font-size: 22px\">\n" +
                "\tO evento: "+evento +" que você está inscrito foi cancelado.\n" +
                "     </td>\n" +
                "    </tr>\n" +
                "   </table>\n" +
                "</body>";
    }
    public String messageInscricaoAceita(String nome,String evento){
        return "<body style=\"margin: 25; padding: 25;\">\n" +
                " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#55DECE\" style=\"padding: 45x 0 25px 0; height: 200px\">\n" +
                "      <img src=\"https://lh3.googleusercontent.com/fife/ABSRlIqwP-BJgfXU27hUF54lelPFy1xxKOepd6NBfvhpR_D5mhnEQXeE9NpSJXny0q2LCBO243YTv8ZsLBfBRdr1d51Qxx6rTFcBaMyAXBAz4bSbPnbbkra8NIbRZBzgOEbwzKwZYxySofo5qG8GYyK4bib0OZOW7-D3H6fZL20LvHfELAs4Vtj2-kB9vLF8Tp7cEM8Dqkv5VUSh5ZSrkAUh7zwOKxu5s4s_SI1-WGemsarUSifBAe6P_9Xh5w9teIuhomziR0VZyBNLlep0I48lbUcO0yvPYoc4koO1LngkW9c2VyiJMWACaNhJ1hIJmocEx7urYJ3QRg7TZPlsWQXeQe_qsUcrKapW0UkmYDaVgdkkMDx4jIIJirN55XGWkGPB9K8a3z4VO8tDb4pnVZvFJEUdKnX0QntbjD4_PVw-lTePh8ImUIh86PhnIfuB84D-55-0ZGQG_bdcw3gDDNjIGzX_QIghYWRxiEDyCz_M1g6mWlh5DEvd6P1NVVa-VlrFD8uHANl3OkX1JvXDGp9X8ARoCJ9LHn72wFQirMNeEuF9GUIg4DfJztKFBUFQC-kYeHzGFDS2hI6YufEnNKzoV-A7M-zf0FYVSElTOS5GxVcY6iUTp7E_tabzE-LWjFSMFZ95OEWfgAc_rO_oB_s0g1a9s8twbvDqGlxD4M4iqBfj4cmLLaOh_aZJ6KR2tIuWGk1deAdR-voSJzvhE_j87PGhFVHYenbAIA=w1920-h976-ft\" style=\"width: 50%;\"/>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"padding: 40px 0 30px 0;\">\n" +
                "     <h2 style=\"font-size:28px\"; margin: auto; margin-top: 0%; margin-bottom: 0%;\">\n" +
                "\tSistema de Gerenciamento de Eventos\n" +
                "     </h2>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "     <td align=\"center\" style=\"padding: 10px 0 10px 0; font-size: 22px\"\n" +
                "\tOlá "+nome+"!\n" +
                "     </td>\n" +
                "   </tr>\n" +
                "   <tr>\n" +
                "     <td align=\"center\" style=\"padding: 30px 0 20px 0; font-size: 22px \">\n" +
                "      Parabéns! Você realizou a sua inscrição no Evento: "+evento+" com sucesso.\n" +
                "     </td>\n" +
                "    </tr>\n" +
                "   </table>\n" +
                "</body>";
    }
    public String messageInscricaoRejeitado(String nome,String evento){
        return "<body style=\"margin: 25; padding: 25;\">\n" +
                " <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#55DECE\" style=\"padding: 45x 0 25px 0; height: 200px\">\n" +
                "      <img src=\"https://lh3.googleusercontent.com/fife/ABSRlIqwP-BJgfXU27hUF54lelPFy1xxKOepd6NBfvhpR_D5mhnEQXeE9NpSJXny0q2LCBO243YTv8ZsLBfBRdr1d51Qxx6rTFcBaMyAXBAz4bSbPnbbkra8NIbRZBzgOEbwzKwZYxySofo5qG8GYyK4bib0OZOW7-D3H6fZL20LvHfELAs4Vtj2-kB9vLF8Tp7cEM8Dqkv5VUSh5ZSrkAUh7zwOKxu5s4s_SI1-WGemsarUSifBAe6P_9Xh5w9teIuhomziR0VZyBNLlep0I48lbUcO0yvPYoc4koO1LngkW9c2VyiJMWACaNhJ1hIJmocEx7urYJ3QRg7TZPlsWQXeQe_qsUcrKapW0UkmYDaVgdkkMDx4jIIJirN55XGWkGPB9K8a3z4VO8tDb4pnVZvFJEUdKnX0QntbjD4_PVw-lTePh8ImUIh86PhnIfuB84D-55-0ZGQG_bdcw3gDDNjIGzX_QIghYWRxiEDyCz_M1g6mWlh5DEvd6P1NVVa-VlrFD8uHANl3OkX1JvXDGp9X8ARoCJ9LHn72wFQirMNeEuF9GUIg4DfJztKFBUFQC-kYeHzGFDS2hI6YufEnNKzoV-A7M-zf0FYVSElTOS5GxVcY6iUTp7E_tabzE-LWjFSMFZ95OEWfgAc_rO_oB_s0g1a9s8twbvDqGlxD4M4iqBfj4cmLLaOh_aZJ6KR2tIuWGk1deAdR-voSJzvhE_j87PGhFVHYenbAIA=w1920-h976-ft\" style=\"width: 50%;\"/>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "   <td align=\"center\" bgcolor=\"#FFFFFF\" style=\"padding: 40px 0 30px 0;\">\n" +
                "     <h2 style=\"font-size: 28px; margin: auto; margin-top: 0%; margin-bottom: 0%;\">\n" +
                "\tSistema de Gerenciamento de Eventos\n" +
                "     </h2>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "     <td align=\"center\" style=\"padding: 10px 0 10px 0; font-size: 22px\"/>\n" +
                "\tOlá "+nome+"!\n" +
                "     </td>\n" +
                "   </tr>\n" +
                "   <tr>\n" +
                "     <td align=\"center\" style=\"padding: 30px 0 20px 0; font-size: 22px \">\n" +
                "\tVocê cancelou a sua inscrição do Evento: "+evento+" com sucesso.\n" +
                "     </td>\n" +
                "    </tr>\n" +
                "   </table>\n" +
                "</body>";
    }

}
