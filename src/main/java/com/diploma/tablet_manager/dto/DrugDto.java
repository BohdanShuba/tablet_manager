package com.diploma.tablet_manager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DrugDto {

    @ApiModelProperty(notes = "The database generated drug ID")
    private int id;

    @ApiModelProperty(notes = "The drug name")
    private String name;

    @ApiModelProperty(notes = "The drug instruction")
    private String instruction;

    @ApiModelProperty(notes = "The drug classification Id")
    private int classificationId;

    public DrugDto(String name, String instruction, int classificationId) {
        this.name = name;
        this.instruction = instruction;
        this.classificationId = classificationId;
    }
}
