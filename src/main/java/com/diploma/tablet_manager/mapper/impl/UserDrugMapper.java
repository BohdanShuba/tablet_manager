package com.diploma.tablet_manager.mapper.impl;

import com.diploma.tablet_manager.domain.UserDrug;
import com.diploma.tablet_manager.domain.UserDrugQuantity;
import com.diploma.tablet_manager.dto.DrugQuantityDto;
import com.diploma.tablet_manager.dto.UserDrugWithQuantityDto;
import com.diploma.tablet_manager.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserDrugMapper implements Mapper<UserDrugWithQuantityDto, UserDrug> {
    private final Mapper<DrugQuantityDto, UserDrugQuantity> userDrugQuantityMapper;

    @Override
    public UserDrugWithQuantityDto toDto(UserDrug userDrug) {
        if (isNull(userDrug)) return null;
        UserDrugWithQuantityDto userDrugWithQuantityDto = new UserDrugWithQuantityDto();
        userDrugWithQuantityDto.setId(userDrug.getId());
        userDrugWithQuantityDto.setName(userDrug.getDrug().getName());
        userDrugWithQuantityDto.setInstruction(userDrug.getDrug().getInstruction());
        userDrugWithQuantityDto.setDrugsQuantityDto(toSet(userDrug.getQuantityList()));
        return userDrugWithQuantityDto;

    }

    private Set<DrugQuantityDto> toSet(Set<UserDrugQuantity> userDrugQuantitySet) {
        Set<DrugQuantityDto> drugQuantityDto = new HashSet<>();
        for (UserDrugQuantity drugQuantity : userDrugQuantitySet) {
            drugQuantityDto.add(userDrugQuantityMapper.toDto(drugQuantity));
        }
        return drugQuantityDto;
    }
}
