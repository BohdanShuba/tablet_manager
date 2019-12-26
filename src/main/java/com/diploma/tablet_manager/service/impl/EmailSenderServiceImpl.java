package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.ConfirmationToken;
import com.diploma.tablet_manager.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(String userEmail, ConfirmationToken confirmationToken) {
        SimpleMailMessage email = createEmailMessage(userEmail, confirmationToken);
        javaMailSender.send(email);
    }

    private SimpleMailMessage createEmailMessage(String userEmail, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("tableton.service@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/registration/confirm-account?token=" + confirmationToken.getConfirmationToken());
        return mailMessage;
    }

}
