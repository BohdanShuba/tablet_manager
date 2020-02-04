package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.Classification;
import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.domain.UserDrugQuantity;
import com.diploma.tablet_manager.dto.*;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/drug/user")
@RequiredArgsConstructor
public class DrugController {

    private final DrugServiceImpl drugServiceImpl;

    @PostMapping("add")
    public String addDrugUser(@ModelAttribute DrugQuantityDto drugQuantityDto, Integer drugId, Map<String, Object> model) {
        drugServiceImpl.addDrugToUser(drugId, drugQuantityDto.getQuantity(), drugQuantityDto.getExpirationDate());
        return "main";
    }

    @PostMapping("take")
    public String takeDrugUser(@RequestParam Integer quantityDrugTaken, Integer userDrugQuantityId, Map<String, Object> model) {
        drugServiceImpl.changeQuantity(userDrugQuantityId, quantityDrugTaken);
        return "redirect:/drug/user/home";
    }

    @GetMapping("/home")
    public String getAllDrugForUser(Map<String, Object> model) {
        try {
            List<UserDrugWithQuantityDto> response = drugServiceImpl.getAllDrugsForUser();
            model.put("drugForUser", response);
            log.debug("drugForUser response: " + response);
        } catch (Exception e) {
            log.error("Cannot obtain drugForUser", e);
        }
        return "home";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model, Integer page, Integer limit) {
        try {
            log.info("Drug request. Page: " + page + ", limit: " + limit);
            Page<Drug> response = drugServiceImpl.getPageDrugs(Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(10));
            List<PageDto> pageDto = drugServiceImpl.getPagesNumbers(response);
            model.put("page", response);
            model.put("pageDto", pageDto);
            log.info("Drug response size: " + Optional.ofNullable(response.getContent()).orElse(Collections.emptyList()).size());
            log.debug("Drug response: " + response.getContent());
        } catch (Exception e) {
            log.error("Cannot obtain drugs", e);
        }
        return "main";
    }

    @GetMapping("classification")
    public String getClassification(Map<String, Object> model) {
        List<ClassificationDto> response = drugServiceImpl.getAllClassifications();
        model.put("classification", response);
        return "drugClassification";
    }

    @GetMapping("drugs-classification")
    public String getDrugsClassification(Integer page, Integer limit, @RequestParam Integer id, Map<String, Object> model) {
        log.info("Classification request. Classification id " + id + " Page:" + page + ", limit: " + limit);
        Page<Drug> response = drugServiceImpl.getPageDrugsClassification(id, Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(10));
        List<PageDto> pageDto = drugServiceImpl.getPagesNumbers(response);
        model.put("page", response);
        model.put("pageDto", pageDto);
        return "main";
    }

    @GetMapping("/addr")
    public String addDrugUser(@RequestParam String name, Integer id, Map<String, Object> model) {
        model.put("id", id);
        model.put("name", name);
        return "addUserDrug";
    }


    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        try {
            List<DrugDto> response = drugServiceImpl.findByNameDrugs(filter);
            model.put("drugs", response);
            log.info("Drug response: " + response);
        } catch (Exception e) {
            log.error("Cannot obtain drugs", e);
        }
        return "filteredDrugs";
    }
}

