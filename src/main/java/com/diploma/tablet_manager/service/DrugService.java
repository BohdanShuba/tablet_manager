package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.Classification;
import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.domain.UserDrug;
import com.diploma.tablet_manager.domain.UserDrugQuantity;
import com.diploma.tablet_manager.dto.DrugDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface DrugService {
    List<Drug> getAllDrugs();

    Drug addNewDrug(DrugDto drugDto);

    List<Drug> findByNameDrugs(String filter);

    Page<Drug> getPageDrugs(int page, int limit);

    Page<Drug> getPageDrugsClassification (int id, int page, int limit);

    Drug findByIdDrug(Integer id);

    List<Classification> getAllClassifications();

    void addDrugToUser(Integer id, Integer quantity, LocalDate expirationDate);

    List<UserDrug> getAllDrugsForUser();

    UserDrugQuantity getUserDrugQuantityById(Integer id);

    void changeQuantity(UserDrugQuantity userDrugQuantity, Integer newDrugCount);
}
