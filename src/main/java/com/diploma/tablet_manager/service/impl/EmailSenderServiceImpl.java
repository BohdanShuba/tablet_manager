package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.ConfirmationToken;
import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.domain.UserDrug;
import com.diploma.tablet_manager.dto.EmailMessageDto;
import com.diploma.tablet_manager.repos.UserDrugRepository;
import com.diploma.tablet_manager.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final UserDrugRepository userDrugRepository;
    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendExpirationEmail() {
        LocalDate date = LocalDate.now();
        LocalDate tomorrowsDate = date.plusDays(1);
        List<UserDrug> userDrugs = userDrugRepository.findAllByExpirationDate(tomorrowsDate);
        for (UserDrug userDrug : userDrugs) {
            StringBuffer drugs = getExpiredUserDrugs(userDrug.getUser().getLogin(), userDrugs);
            EmailMessageDto emailMessageDto = createExpirationEmail(userDrug.getUser().getEmail(), drugs.toString(), tomorrowsDate);
            SimpleMailMessage email = createSimpleMailMessage(emailMessageDto);
            javaMailSender.send(email);
            System.out.println(emailMessageDto.toString());
        }
    }

    private StringBuffer getExpiredUserDrugs(String userLogin, List<UserDrug> userDrug) {
        StringBuffer drugs = new StringBuffer();
        for (int i = 0; i < userDrug.size() - 1; i++) {
            String login = userDrug.get(i).getUser().getLogin();
            if (login.equals(userLogin)) {
                String nameDrug = userDrug.get(i).getDrug().getName();
                drugs.append(nameDrug);
                drugs.append(", ");
            }
        }
        return drugs.deleteCharAt(drugs.length() - 2);
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

    private EmailMessageDto createExpirationEmail(String email, String drugs, LocalDate date) {
        EmailMessageDto emailMessageDto = new EmailMessageDto();
        emailMessageDto.setRecipient(email);
        emailMessageDto.setSubject("Срок годности лекарства истекает");
        emailMessageDto.setText("Здравствуйте! Обратите внимание, срок годности лекарства " + drugs + "истекает завтра, а именно: " + date.toString());
        return emailMessageDto;
    }
}
