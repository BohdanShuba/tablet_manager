package com.diploma.tablet_manager.mapper.impl;

import com.diploma.tablet_manager.domain.Classification;
import com.diploma.tablet_manager.dto.ClassificationDto;
import com.diploma.tablet_manager.mapper.Mapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ClassificationMapper implements Mapper<ClassificationDto, Classification> {

    @Override
    public ClassificationDto toDto(Classification classification) {
        if (isNull(classification)) {
            return null;
        }
        ClassificationDto classificationDto = new ClassificationDto();
        classificationDto.setId(classification.getId());
        classificationDto.setAtcCode(classification.getAtcCode());
        classificationDto.setCodeDescription(classification.getCodeDescription());
        return classificationDto;
    }
}
