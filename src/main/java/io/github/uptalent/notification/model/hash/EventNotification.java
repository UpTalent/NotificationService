package io.github.uptalent.notification.model.hash;


import io.github.uptalent.notification.model.common.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("event_notification")
public class EventNotification implements Serializable {
    @Id
    private String uuid;
    private Author from;
    @Indexed
    private String to;
    private String message;
    private String contentLink;
    private Boolean isRead;
    private LocalDateTime notified;
}
