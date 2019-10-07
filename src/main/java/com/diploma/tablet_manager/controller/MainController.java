package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.dto.PageDto;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Controller
@RequiredArgsConstructor
public class MainController {

    private final DrugServiceImpl drugServiceImpl;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/admin/adm")
    public String adm(Map<String, Object> model) {
        return "admpage";
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


    @PostMapping("/admin/adm")
    public String add(@ModelAttribute DrugDto drugDto, Map<String, Object> model) {
        System.out.println("Хули блять?");
        try {
            log.info("Add drug. Drug: " + drugDto);
            drugServiceImpl.addNewDrug(drugDto);
            List<Drug> response = drugServiceImpl.getAllDrugs();
            model.put("drugs", response);
            log.info("Drug response: " + response);
        } catch (Exception e) {
            log.error("Cannot add drug", e);
        }
        return "admpage";
    }


    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        try {
            List<Drug> response = drugServiceImpl.findByNameDrugs(filter);
            model.put("drugs", response);
            log.info("Drug response: " + response);
        } catch (Exception e) {
            log.error("Cannot obtain drugs", e);
        }
        return "main";
    }
}

