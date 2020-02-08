package com.diploma.tablet_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDrugWithQuantityDto {
    private int id;
    private String name;
    private String instruction;
    Set<DrugQuantityDto> drugsQuantityDto;
}
