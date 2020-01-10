package com.diploma.tablet_manager.repos;

import com.diploma.tablet_manager.domain.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByName(String name);

    Drug findById(Integer id);

    Page<Drug> findAllByClassificationId(Integer id, Pageable pageRequest);
}
