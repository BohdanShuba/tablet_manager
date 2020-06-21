package com.diploma.tablet_manager.mapper.impl;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.mapper.Mapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class DrugMapper implements Mapper<DrugDto, Drug> {

    @Override
    public DrugDto toDto(Drug drug) {
        if (isNull(drug)) {
            return null;
        }
        DrugDto drugDto = new DrugDto();
        drugDto.setId(drug.getId());
        drugDto.setName(drug.getName());
        drugDto.setInstruction(drug.getInstruction());
        drugDto.setClassificationId(drug.getClassification().getId());
        return drugDto;
    }
}

