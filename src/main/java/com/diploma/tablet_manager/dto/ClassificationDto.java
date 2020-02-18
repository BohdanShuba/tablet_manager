package com.diploma.tablet_manager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClassificationDto {

    @ApiModelProperty(notes = "The database generated classification ID")
    private int id;

    @ApiModelProperty(notes = "The classification ATC code")
    private char atcCode;

    @ApiModelProperty(notes = "The classification code description")
    private String codeDescription;
}
