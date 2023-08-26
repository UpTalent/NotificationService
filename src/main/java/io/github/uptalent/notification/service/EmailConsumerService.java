package io.github.uptalent.notification.service;

import io.github.uptalent.notification.model.common.EmailMessage;
import io.github.uptalent.starter.model.common.EmailMessageDetailInfo;
import io.github.uptalent.starter.model.common.EmailMessageGeneralInfo;
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

    @RabbitListener(queues = {"${rabbitmq.queue.blocked-account}"})
    public void consumeBlockedAccountMessage(EmailMessageGeneralInfo emailMessageGeneralInfo) {
        EmailMessage emailMessage = emailMessageGeneratorService
                .generateEmailMessage(emailMessageGeneralInfo, BLOCK_ACCOUNT);
        emailSenderService.sendEmail(emailMessage);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.unblocked-account}"})
    public void consumeUnblockedAccountMessage(EmailMessageGeneralInfo emailMessageGeneralInfo) {
        EmailMessage emailMessage = emailMessageGeneratorService
                .generateEmailMessage(emailMessageGeneralInfo, UNBLOCK_ACCOUNT);
        emailSenderService.sendEmail(emailMessage);
    }
}
