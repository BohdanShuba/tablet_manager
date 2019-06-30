package com.diploma.tablet_manager.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class DrugDto {

    private String name;
    private String instruction;
    private int classificationId;
}
