package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.Classification;
import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.repos.ClassificationRepository;
import com.diploma.tablet_manager.repos.DrugRepository;
import com.diploma.tablet_manager.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final ClassificationRepository classificationRepository;

    @Override
    public List<Drug> findAllDrugs() {
        return drugRepository.findAll();
    }

    @Override
    public Drug addNewDrug(DrugDto drugDto) {
        Classification classification = classificationRepository.findAllById(drugDto.getClassificationId());
        Drug drug = new Drug(drugDto.getName(), drugDto.getInstruction(), classification);
        return drugRepository.save(drug);
    }

    @Override
    public List<Drug> findByNameDrugs(String nameDrug) {
        if (nameDrug != null && !nameDrug.isEmpty()) {
            return drugRepository.findByName(nameDrug);
        } else {
            return drugRepository.findAll();
        }
    }
}
