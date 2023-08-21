package io.github.uptalent.notification.ws;

import io.github.uptalent.notification.model.hash.EventNotification;
import io.github.uptalent.notification.service.EventNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventNotificationWebSocketController {
    public static final String SEND_NOTIFICATION_TO_USER = "/user/notification.{user-key}.send";
    public static final String MARK_ALL_NOTIFICATIONS_AS_READ_FOR_USER = "/user/notification.{user-key}.read.mark.all";
    public static final String MARK_ONE_NOTIFICATION_AS_READ_FOR_USER = "/user/notification.{user-key}.read.mark.{notification-id}";
    public static final String FETCH_NOTIFICATIONS_FOR_USER = "/user/notification.{user-key}.fetch";

    private final EventNotificationService eventNotificationService;

    @SubscribeMapping(FETCH_NOTIFICATIONS_FOR_USER)
    public List<EventNotification> fetchNotificationsByUser(@DestinationVariable("user-key") String userKey) {
        return eventNotificationService.getNotificationsByUser(userKey);
    }

    @MessageMapping(MARK_ALL_NOTIFICATIONS_AS_READ_FOR_USER)
    public void markAllNotificationsAsReadByUser(
            @DestinationVariable("user-key") String userKey) {
        eventNotificationService.markAsReadAllNotificationsByUser(userKey);
    }

    @MessageMapping(MARK_ONE_NOTIFICATION_AS_READ_FOR_USER)
    public void markOneNotificationAsReadByUser(
            @DestinationVariable("user-key") String userKey,
            @DestinationVariable("notification-id") String notificationId) {
        eventNotificationService.markAsReadOneNotificationByUser(notificationId, userKey);
    }
}
