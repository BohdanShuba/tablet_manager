package com.diploma.tablet_manager.mapper.impl;

import com.diploma.tablet_manager.domain.UserDrugQuantity;
import com.diploma.tablet_manager.dto.DrugQuantityDto;
import com.diploma.tablet_manager.mapper.Mapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserDrugQuantityMapper implements Mapper<DrugQuantityDto, UserDrugQuantity> {

    @Override
    public DrugQuantityDto toDto(UserDrugQuantity userDrugQuantity) {
        if (isNull(userDrugQuantity)) {
            return null;
        }
        DrugQuantityDto drugQuantityDto = new DrugQuantityDto();
        drugQuantityDto.setId(userDrugQuantity.getId());
        drugQuantityDto.setQuantity(userDrugQuantity.getQuantity());
        drugQuantityDto.setExpirationDate(userDrugQuantity.getExpirationDate());
        return drugQuantityDto;
    }
}
