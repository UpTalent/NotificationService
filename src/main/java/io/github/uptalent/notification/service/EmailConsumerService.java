package io.github.uptalent.notification.service;

import io.github.uptalent.notification.model.common.EmailMessage;
import io.github.uptalent.notification.model.common.EmailMessageDetailInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static io.github.uptalent.notification.model.constant.EmailMessageLinkType.*;

@Service
@RequiredArgsConstructor
public class EmailConsumerService {

    private final EmailMessageGeneratorService emailMessageGeneratorService;
    private final EmailSenderService emailSenderService;

    @RabbitListener(queues = {"${rabbitmq.queue.change.password}"})
    public void consumeChangePasswordMessage(EmailMessageDetailInfo emailMessageDetailInfo) {
        EmailMessage emailMessage = emailMessageGeneratorService
                .generateEmailMessage(emailMessageDetailInfo, CHANGE_PASSWORD);
        emailSenderService.sendEmail(emailMessage);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.verify}"})
    public void consumeVerifyMessage(EmailMessageDetailInfo emailMessageDetailInfo) {
        EmailMessage emailMessage = emailMessageGeneratorService
                .generateEmailMessage(emailMessageDetailInfo, VERIFY);
        emailSenderService.sendEmail(emailMessage);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.restore-account}"})
    public void consumeRestoreAccountMessage(EmailMessageDetailInfo emailMessageDetailInfo) {
        EmailMessage emailMessage = emailMessageGeneratorService
                .generateEmailMessage(emailMessageDetailInfo, RESTORE);
        emailSenderService.sendEmail(emailMessage);
    }
}
