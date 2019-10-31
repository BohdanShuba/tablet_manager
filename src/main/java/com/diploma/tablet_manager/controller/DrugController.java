package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.domain.UserDrug;
import com.diploma.tablet_manager.dto.PageDto;
import com.diploma.tablet_manager.dto.UserDrugDto;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
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
public class DrugController {

    private final DrugServiceImpl drugServiceImpl;

    @PostMapping("/drug/user/add")
    public String addDrugUser(@ModelAttribute UserDrugDto userDrugDto, Map<String, Object> model) {
        drugServiceImpl.addDrugToUser(userDrugDto.getDrugId(), userDrugDto.getQuantity(), userDrugDto.getExpirationDate());
        return "main";
    }

    @GetMapping("/home")
    public String getAllDrugForUser(Map<String, Object> model) {
        try {
        List <UserDrug> response = drugServiceImpl.getAllDrugsForUser();
        model.put("drugForUser",response);
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

    @GetMapping("/addr")
    public String addDrugUser(@RequestParam String name, Integer id, Map<String, Object> model) {
        model.put("id", id);
        model.put("name", name);
        return "addUserDrug";
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

