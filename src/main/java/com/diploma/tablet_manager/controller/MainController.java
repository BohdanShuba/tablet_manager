package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final DrugServiceImpl drugServiceImpl;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        List<Drug> response = drugServiceImpl.findAllDrugs();
        model.put("drugs", response); // model.put("drugs", drugServiceImpl.getAllDrugs());
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String instruction, @RequestParam int id_purpose, Map<String, Object> model) {
        drugServiceImpl.addNewDrug(name, instruction, id_purpose);
        List<Drug> response = drugServiceImpl.findAllDrugs();
        model.put("drugs", response);
        return "main";
    }


    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Drug> response = drugServiceImpl.findByNameDrugs(filter);
        model.put("drugs", response);
        return "main";
    }
}
