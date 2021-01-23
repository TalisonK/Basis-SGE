package com.basis.sge.service.servico.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SgeSource {
    String BINDING_MAILER = "mailer";

    @Output(SgeSource.BINDING_MAILER)
    MessageChannel enviarEmail();

}