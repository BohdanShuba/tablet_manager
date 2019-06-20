package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.Role;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.LoginDto;
import com.diploma.tablet_manager.repos.UserRepository;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public User addNewUser(LoginDto loginDto) {
        User user = new User(loginDto.getLogin(), passwordEncoder.encode(loginDto.getPassword()));
        user.setEnabled(true);
        user.setRole(Collections.singleton(Role.USER));
        return userRepository.saveAndFlush(user);
    }

}
