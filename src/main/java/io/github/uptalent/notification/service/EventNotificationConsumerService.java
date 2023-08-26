package io.github.uptalent.notification.service;

import io.github.uptalent.starter.model.common.EventNotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventNotificationConsumerService {
    private final EventNotificationService eventNotificationService;
    @RabbitListener(queues = {"${rabbitmq.queue.event_notification}"})
    public void consumeEventNotificationMessage(EventNotificationMessage eventNotificationMessage) {
        eventNotificationService.sendNotificationToUser(eventNotificationMessage);
    }
}
