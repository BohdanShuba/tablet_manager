package com.diploma.tablet_manager.repos;

import com.diploma.tablet_manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    User findByLoginOrEmailIgnoreCase(String login, String email);

    //  User findByExpirationDate(LocalDate date);
}
