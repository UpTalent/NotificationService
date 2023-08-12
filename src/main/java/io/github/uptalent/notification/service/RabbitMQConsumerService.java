package io.github.uptalent.notification.service;

import io.github.uptalent.notification.model.SendEmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumerService {

    private final EmailSenderService emailSenderService;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(SendEmailMessage emailMessage) {
        emailSenderService.sendEmail(emailMessage);
    }
}
