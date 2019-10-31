package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.LoginDto;

public interface UserService {
    User findUserByLogin(String login);
    User addNewUser(LoginDto loginDto);
}
