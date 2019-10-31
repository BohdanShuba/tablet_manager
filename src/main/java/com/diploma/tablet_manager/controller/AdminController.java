package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.service.DrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {

    private final DrugService drugServiceImpl;

    @GetMapping("/administration")
    public String adm(Map<String, Object> model) {
        return "administrationPage";
    }


    @PostMapping("/administration")
    public String add(@ModelAttribute DrugDto drugDto, Map<String, Object> model) {
        try {
            log.info("Add drug. Drug: " + drugDto);
            drugServiceImpl.addNewDrug(drugDto);
            List<Drug> response = drugServiceImpl.getAllDrugs();
            model.put("drugs", response);
            log.info("Drug response: " + response);
        } catch (Exception e) {
            log.error("Cannot add drug", e);
        }
        return "administrationPage";
    }

}
