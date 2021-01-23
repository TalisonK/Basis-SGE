package com.basis.sge.service.servico.producer;

import com.basis.sge.service.servico.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@EnableBinding(SgeSource.class)
@Service
@RequiredArgsConstructor
public class SgeProducer {

    private final SgeSource sgeSource;

    public void enviarEmail(EmailDTO emailDTO) {
        Message<EmailDTO> message = MessageBuilder.withPayload(emailDTO).build();
        this.sgeSource.enviarEmail().send(message);
    }

}