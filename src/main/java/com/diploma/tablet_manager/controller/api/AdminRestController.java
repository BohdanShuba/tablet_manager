package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.service.DrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final DrugService drugServiceImpl;

    @PostMapping
    public DrugDto add(DrugDto drugDto) {
        log.info("Add drug. Drug: " + drugDto);
        return drugServiceImpl.addNewDrug(drugDto);
    }
}
