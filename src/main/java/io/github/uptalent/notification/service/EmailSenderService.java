package io.github.uptalent.notification.service;

import io.github.uptalent.notification.model.SendEmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailSenderService {
    @Value("${spring.mail.username}")
    private String emailAddressFrom;
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SendEmailMessage request) {
        if (Objects.isNull(request))
            return;

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);

        try {
            helper.setFrom(emailAddressFrom);
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getText());
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
