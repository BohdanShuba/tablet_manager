package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;

public interface ConfirmationTokenService {
    void sendConfirmationToken(User user);

    String confirmUserAccount(String confirmationToken);
}
