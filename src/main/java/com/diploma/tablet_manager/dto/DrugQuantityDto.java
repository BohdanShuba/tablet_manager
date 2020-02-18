package com.diploma.tablet_manager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DrugQuantityDto {

    @ApiModelProperty(notes = "The database generated drugQuantity ID")
    private int id;

    @ApiModelProperty(notes = "The drug quantity")
    private int quantity;

    @ApiModelProperty(notes = "The drug expiration date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;
}
