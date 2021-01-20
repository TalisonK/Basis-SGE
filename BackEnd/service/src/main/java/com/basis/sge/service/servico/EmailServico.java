package com.basis.sge.service.servico;

import com.basis.sge.service.configuracao.ApplicationProperties;
import com.basis.sge.service.servico.dto.EmailDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


@Service
@RequiredArgsConstructor
@Getter
@Setter
public class EmailServico {

    private static final String ERROR_TITLE = "error.title";
    private final JavaMailSender javaMailSender;

    private final ApplicationProperties properties;

    public void sendMail(EmailDTO emailDTO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setTo("kenouen1@gmail.com");
            message.setFrom(emailDTO.getDestinatario(), "Sistema de Gerenciamento de Evento");
            message.setSubject(emailDTO.getAssunto());
            for (String s : emailDTO.getCopias()) {
                message.addCc(s);
            }
            message.setText(emailDTO.getCorpo(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(ERROR_TITLE);
        }
    }
}
