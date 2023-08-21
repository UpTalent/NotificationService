package io.github.uptalent.notification.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventNotificationMessage {
    private Author from;
    private String to;
    private String message;
    private String contentLink;
}
