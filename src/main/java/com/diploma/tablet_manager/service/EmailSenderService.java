package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.ConfirmationToken;

public interface EmailSenderService {
    void sendEmail(String userEmail, ConfirmationToken confirmationToken);

    void sendExpirationEmail();
}
