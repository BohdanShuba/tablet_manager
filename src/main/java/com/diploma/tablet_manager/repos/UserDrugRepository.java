package com.diploma.tablet_manager.repos;

import com.diploma.tablet_manager.domain.UserDrug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDrugRepository extends JpaRepository<UserDrug, Long> {
    UserDrug findByUserIdAndDrugId (Integer userId, Integer drugId);
}