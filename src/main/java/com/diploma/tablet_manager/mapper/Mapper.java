package com.diploma.tablet_manager.mapper;

import com.diploma.tablet_manager.domain.*;
import com.diploma.tablet_manager.dto.*;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public class Mapper {

    public DrugDto toDto(Drug drug) {
        if (isNull(drug)) return null;
        DrugDto drugDto = new DrugDto();
        drugDto.setId(drug.getId());
        drugDto.setName(drug.getName());
        drugDto.setInstruction(drug.getInstruction());
        drugDto.setClassificationId(drug.getClassification().getId());
        return drugDto;
    }

    public DrugQuantityDto toDto(UserDrugQuantity userDrugQuantity) {
        if (isNull(userDrugQuantity)) return null;
        DrugQuantityDto drugQuantityDto = new DrugQuantityDto();
        drugQuantityDto.setId(userDrugQuantity.getId());
        drugQuantityDto.setQuantity(userDrugQuantity.getQuantity());
        drugQuantityDto.setExpirationDate(userDrugQuantity.getExpirationDate());
        return drugQuantityDto;
    }

    public UserDto toDto(User user) {
        if (isNull(user)) return null;
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public UserDrugWithQuantityDto toDto(UserDrug userDrug) {
        if (isNull(userDrug)) return null;
        UserDrugWithQuantityDto userDrugWithQuantityDto = new UserDrugWithQuantityDto();
        userDrugWithQuantityDto.setId(userDrug.getId());
        userDrugWithQuantityDto.setName(userDrug.getDrug().getName());
        userDrugWithQuantityDto.setInstruction(userDrug.getDrug().getInstruction());
        userDrugWithQuantityDto.setDrugsQuantityDto(toSet(userDrug.getQuantityList()));
        return userDrugWithQuantityDto;

    }

    public ClassificationDto toDto(Classification classification) {
        if (isNull(classification)) return null;
        ClassificationDto classificationDto = new ClassificationDto();
        classificationDto.setId(classification.getId());
        classificationDto.setAtcCode(classification.getAtcCode());
        classificationDto.setCodeDescription(classification.getCodeDescription());
        return classificationDto;
    }

    private Set<DrugQuantityDto> toSet(Set<UserDrugQuantity> userDrugQuantitySet) {
        Set<DrugQuantityDto> drugQuantityDto = new HashSet<>();
        for (UserDrugQuantity drugQuantity :
                userDrugQuantitySet) {
            drugQuantityDto.add(toDto(drugQuantity));
        }
        return drugQuantityDto;
    }
}
