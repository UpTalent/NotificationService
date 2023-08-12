package io.github.uptalent.notification.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailMessage {
    private String to;
    private String subject;
    private String text;
}
