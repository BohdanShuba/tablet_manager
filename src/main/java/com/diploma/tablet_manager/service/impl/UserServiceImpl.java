package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.Role;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.repos.UserRepository;
import com.diploma.tablet_manager.service.ConfirmationTokenService;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findUserByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmailIgnoreCase(login, email);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = findUserByLogin(currentUserName);
        return currentUser;
    }


    @Override
    @Transactional
    public User addNewUser(UserDto userDto) {
        User user = new User(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setRole(Collections.singleton(Role.USER));
        userRepository.saveAndFlush(user);
        confirmationTokenService.sendConfirmationToken(user);
        return user;
    }

}
