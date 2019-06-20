package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.repos.DrugRepository;
import com.diploma.tablet_manager.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;

    public List<Drug> findAllDrugs() {
        return drugRepository.findAll();
    }

    public Drug addNewDrug(String name, String instruction, int id_purpose) {
        Drug drug = new Drug(name, instruction, id_purpose);
        return drugRepository.save(drug);
    }

    public List<Drug> findByNameDrugs(String nameDrug) {
        if (nameDrug != null && !nameDrug.isEmpty()) {
            return drugRepository.findByName(nameDrug);
        } else {
            return drugRepository.findAll();
        }
    }
}
