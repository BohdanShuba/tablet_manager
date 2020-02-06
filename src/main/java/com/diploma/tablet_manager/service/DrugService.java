package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.domain.UserDrugQuantity;
import com.diploma.tablet_manager.dto.ClassificationDto;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.dto.DrugQuantityDto;
import com.diploma.tablet_manager.dto.UserDrugWithQuantityDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface DrugService {
    List<Drug> getAllDrugs();

    DrugDto addNewDrug(DrugDto drugDto);

    List<DrugDto> findByNameDrugs(String filter);

    Page<Drug> getPageDrugs(int page, int limit);

    Page<Drug> getPageDrugsClassification(int id, int page, int limit);

    Drug findByIdDrug(Integer id);

    List<ClassificationDto> getAllClassifications();

    DrugQuantityDto addDrugToUser(Integer id, Integer quantity, LocalDate expirationDate);

    List<UserDrugWithQuantityDto> getAllDrugsForUser();

    UserDrugQuantity getUserDrugQuantityById(Integer id);

    DrugQuantityDto changeQuantity(Integer userDrugQuantityId, Integer newDrugCount);
}
