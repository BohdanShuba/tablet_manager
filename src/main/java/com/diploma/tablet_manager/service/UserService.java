package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;

public interface UserService {
    User findUserByLogin(String login);
}
