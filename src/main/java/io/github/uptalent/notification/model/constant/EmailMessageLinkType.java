package io.github.uptalent.notification.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailMessageLinkType {
    VERIFY("/verify"),
    RESTORE("/restore"),
    CHANGE_PASSWORD("/password/change");

    private final String url;
}
