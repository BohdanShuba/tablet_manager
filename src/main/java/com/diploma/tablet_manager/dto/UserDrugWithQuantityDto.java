package com.diploma.tablet_manager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDrugWithQuantityDto {

    @ApiModelProperty(notes = "The database generated UserDrugQuantity ID")
    private int id;

    @ApiModelProperty(notes = "The drug name")
    private String name;

    @ApiModelProperty(notes = "The drug instruction")
    private String instruction;

    @ApiModelProperty(notes = "Set of quantity and expiration date of drugs")
    Set<DrugQuantityDto> drugsQuantityDto;
}
