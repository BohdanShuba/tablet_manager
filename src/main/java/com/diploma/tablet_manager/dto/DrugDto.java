package com.diploma.tablet_manager.dto;

import com.diploma.tablet_manager.domain.Classification;
import lombok.Data;

@Data
public class DrugDto {

    private int id;
    private String name;
    private String instruction;
    private int classificationId;
}
