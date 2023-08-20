package io.github.uptalent.notification.service;

import io.github.uptalent.notification.model.common.EventNotificationMessage;
import io.github.uptalent.notification.model.hash.EventNotification;
import io.github.uptalent.notification.repository.EventNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.uptalent.notification.ws.EventNotificationWebSocketController.SEND_NOTIFICATION_TO_USER;

@Service
@RequiredArgsConstructor
public class EventNotificationService {
    private final EventNotificationRepository eventNotificationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendNotificationToUser(EventNotificationMessage message) {
        String uuid = UUID.randomUUID().toString();
        String to = message.getTo();
        EventNotification notification = EventNotification.builder()
                .uuid(uuid)
                .from(message.getFrom())
                .to(to)
                .message(message.getMessage())
                .contentLink(message.getContentLink())
                .isRead(false)
                .notified(LocalDateTime.now())
                .build();

        eventNotificationRepository.save(notification);

        String destination = SEND_NOTIFICATION_TO_USER.replace("{user-key}", to);
        simpMessagingTemplate.convertAndSend(destination, notification);
    }

    public List<EventNotification> getNotificationsByUser(String userKey) {
        return eventNotificationRepository.findAllByToOrderByNotifiedDesc(userKey);
    }

    public void markAsReadAllNotificationsByUser(String userKey) {
        List<EventNotification> eventNotifications = getNotificationsByUser(userKey);
        eventNotifications.forEach(eventNotification -> eventNotification.setIsRead(true));
        eventNotificationRepository.saveAll(eventNotifications);
    }

    public void markAsReadOneNotificationByUser(String uuid, String userKey) {
        eventNotificationRepository.findByUuidAndTo(uuid, userKey)
                .ifPresent(eventNotification -> {
                    eventNotification.setIsRead(true);
                    eventNotificationRepository.save(eventNotification);
                });
    }

}
