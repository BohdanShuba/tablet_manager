package com.diploma.tablet_manager.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserDrugDto {

    private Integer drugId;
    private Integer quantity;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;
}
