package com.basis.sge.service.servico;

import com.basis.sge.service.servico.dto.EmailDTO;
import com.basis.sge.service.servico.sink.SgeSink;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableBinding(SgeSink.class)
public class RabbitService {
    private final EmailServico emailServico;

    @StreamListener(target = SgeSink.BINDING_MAILER)
    public void sendMail(@Payload EmailDTO emailDTO) {
        emailServico.sendMail(emailDTO);
    }
}