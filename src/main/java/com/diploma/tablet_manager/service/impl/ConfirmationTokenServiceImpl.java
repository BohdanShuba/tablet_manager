package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.ConfirmationToken;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.repos.ConfirmationTokenRepository;
import com.diploma.tablet_manager.repos.UserRepository;
import com.diploma.tablet_manager.service.ConfirmationTokenService;
import com.diploma.tablet_manager.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    @Override
    public void sendConfirmationToken(User user) {
        ConfirmationToken confirmationToken = createConfirmationTokenForUser(user);
        emailSenderService.sendEmail(user.getEmail(), confirmationToken);
    }

    @Override
    public String confirmUserAccount(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByLogin(token.getUser().getLogin());
            user.setEnabled(true);
            userRepository.save(user);
            return "Аккаунт подтвержден";
        } else return "Ссылка неверна или не работает";

    }

    private ConfirmationToken createConfirmationTokenForUser(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken;
    }
}
