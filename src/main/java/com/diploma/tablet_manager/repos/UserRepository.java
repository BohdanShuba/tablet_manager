package com.diploma.tablet_manager.repos;

import com.diploma.tablet_manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findByLoginOrEmail(String login, String email);
}
