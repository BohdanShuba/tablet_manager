package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.ConfirmationToken;
import com.diploma.tablet_manager.dto.EmailMessageDto;
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
    public void sendEmail(String userEmail) {
        ConfirmationToken confirmationToken = null;
        EmailMessageDto emailMessageDto = createAccountConfirmationEmailMessage(userEmail, confirmationToken);
        SimpleMailMessage email = createSimpleMailMessage(emailMessageDto);
        javaMailSender.send(email);
    }


    @Override
    @Async
    public void sendEmail(String userEmail, ConfirmationToken confirmationToken) {
        EmailMessageDto emailMessageDto = createAccountConfirmationEmailMessage(userEmail, confirmationToken);
        SimpleMailMessage email = createSimpleMailMessage(emailMessageDto);
        javaMailSender.send(email);
    }

    private SimpleMailMessage createSimpleMailMessage(EmailMessageDto emailMessageDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailMessageDto.getRecipient());
        mailMessage.setSubject(emailMessageDto.getSubject());
        mailMessage.setText(emailMessageDto.getText());
        return mailMessage;
    }

    private EmailMessageDto createAccountConfirmationEmailMessage(String userEmail, ConfirmationToken confirmationToken) {
        EmailMessageDto emailMessageDto = new EmailMessageDto();
        emailMessageDto.setRecipient(userEmail);
        emailMessageDto.setSubject("Complete Registration!");
        emailMessageDto.setText("To confirm your account, please click here : "
                + "http://localhost:8080/registration/confirm-account?token=" + confirmationToken.getConfirmationToken());
        return emailMessageDto;
    }

    private EmailMessageDto createEmailMessage(String userEmail, String nameDrug) {
        EmailMessageDto emailMessageDto = new EmailMessageDto();
        emailMessageDto.setRecipient(userEmail);
        emailMessageDto.setSubject("Complete Registration!");
        emailMessageDto.setText("Здравствуйте! Срок годности лекарства " + nameDrug +"истекает завтра, а именно:");
        return emailMessageDto;
    }
}
