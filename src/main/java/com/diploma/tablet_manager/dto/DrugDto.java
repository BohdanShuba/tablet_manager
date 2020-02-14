package com.diploma.tablet_manager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DrugDto {

    @ApiModelProperty(notes = "The database generated drug ID")
    private int id;

    @ApiModelProperty(notes = "The drug name")
    private String name;

    @ApiModelProperty(notes = "The drug instruction")
    private String instruction;

    @ApiModelProperty(notes = "The drug classification Id")
    private int classificationId;
}
