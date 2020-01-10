package com.diploma.tablet_manager.repos;

import com.diploma.tablet_manager.domain.UserDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserDrugRepository extends JpaRepository<UserDrug, Long> {
    @Query(value = "SELECT DISTINCT * FROM user_drug\n" +
            "JOIN user on user.id = user_drug.user_id\n" +
            "JOIN drug on drug.id = user_drug.drug_id\n" +
            "JOIN user_drug_quantity on user_drug.id = user_drug_quantity.user_drug_id\n" +
            "WHERE user_drug_quantity.expiration_date = :date", nativeQuery = true)
    List<UserDrug> findAllByExpirationDate(@Param("date") LocalDate date);

    UserDrug findByUserIdAndDrugId(Integer userId, Integer drugId);

    List<UserDrug> findByUserId(Integer userId);
}
