package io.github.uptalent.notification.service;

import io.github.uptalent.notification.model.common.EmailMessageDetailInfo;
import io.github.uptalent.notification.model.constant.EmailMessageLinkType;
import io.github.uptalent.notification.model.common.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.github.uptalent.notification.model.constant.EmailMessageConstant.*;

@Service
@RequiredArgsConstructor
public class EmailMessageGeneratorService {
    private static final String TOKEN_PARAMETER = "?token=";

    @Value("${client.url}")
    private String clientUrl;

    public EmailMessage generateEmailMessage(EmailMessageDetailInfo messageInfo, EmailMessageLinkType messageLinkType) {
        String link = generateLink(messageInfo.getUuid(), messageLinkType);
        String message = generateLetter(messageInfo.getUsername(),
                messageInfo.getExpiredDateTime(),
                link,
                messageLinkType);
        String subject = messageLinkType.getSubject();
        String email = messageInfo.getEmail();

        return new EmailMessage(email, subject, message);
    }

    private String generateLetter(String username,
                                  LocalDateTime expiredDateTime,
                                  String link,
                                  EmailMessageLinkType messageType) {
        String messageBody = messageType.getMessageBody();

        return MESSAGE_HEADER +
                messageBody.formatted(username, expiredDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE), link) +
                MESSAGE_FOOTER;
    }

    private String generateLink(String uuid, EmailMessageLinkType messageType) {
        return clientUrl +
                messageType.getUrl() +
                TOKEN_PARAMETER +
                uuid;
    }
}
