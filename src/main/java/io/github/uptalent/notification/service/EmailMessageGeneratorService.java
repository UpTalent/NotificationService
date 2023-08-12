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
    private static final String DEFAULT_SUBJECT = "Default subject";
    private static final String DEFAULT_MESSAGE = "Default message";

    @Value("${client.url}")
    private String clientUrl;

    public EmailMessage generateEmailMessage(EmailMessageDetailInfo messageInfo, EmailMessageLinkType messageLinkType) {
        String link = generateLink(messageInfo.getUuid(), messageLinkType);
        String message = generateLetter(messageInfo.getUsername(),
                messageInfo.getExpiredDateTime(),
                link,
                messageLinkType);
        String subject = getEmailSubjectByMessageLinkType(messageLinkType);
        String email = messageInfo.getEmail();

        return new EmailMessage(email, subject, message);
    }


    private String generateLetter(String username,
                                  LocalDateTime expiredDateTime,
                                  String link,
                                  EmailMessageLinkType messageType) {
        String messageBody = getEmailMessageByMessageLinkType(messageType);

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

    private String getEmailSubjectByMessageLinkType(EmailMessageLinkType messageLinkType) {
        switch (messageLinkType) {
            case CHANGE_PASSWORD -> {
                return SUBJECT_CHANGE_PASSWORD;
            }
            case VERIFY -> {
                return SUBJECT_VERIFY;
            }
            case RESTORE -> {
                return SUBJECT_RESTORE;
            }
            default -> {
                return DEFAULT_SUBJECT;
            }
        }
    }

    private String getEmailMessageByMessageLinkType(EmailMessageLinkType messageLinkType) {
        switch (messageLinkType) {
            case CHANGE_PASSWORD -> {
                return MESSAGE_CHANGE_PASSWORD;
            }
            case VERIFY -> {
                return MESSAGE_VERIFY;
            }
            case RESTORE -> {
                return MESSAGE_RESTORE;
            }
            default -> {
                return DEFAULT_MESSAGE;
            }
        }
    }
}