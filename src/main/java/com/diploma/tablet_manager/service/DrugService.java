package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.DrugDto;

import java.util.List;

public interface DrugService {

    List<Drug> findAllDrugs();
    Drug addNewDrug(DrugDto drugDto);
    List<Drug> findByNameDrugs(String filter);
}
