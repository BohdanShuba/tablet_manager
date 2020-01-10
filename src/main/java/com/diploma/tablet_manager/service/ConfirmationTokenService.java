package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;

public interface ConfirmationTokenService {
    void sendConfirmationToken(User user);

    String confirmUserAccount(String confirmationToken);
}
