package com.diploma.tablet_manager.repos;

import com.diploma.tablet_manager.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findAllById(Integer id);
}
