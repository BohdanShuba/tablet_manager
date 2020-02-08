package com.diploma.tablet_manager.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DrugQuantityDto {

    private int id;
    private int quantity;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;

    //private int drugId;
}
