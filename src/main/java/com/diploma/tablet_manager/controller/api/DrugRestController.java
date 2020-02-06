package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.ClassificationDto;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.dto.DrugQuantityDto;
import com.diploma.tablet_manager.dto.UserDrugWithQuantityDto;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugRestController {

    private final DrugServiceImpl drugServiceImpl;

    @GetMapping(params = {"page", "size"})
    public Page<Drug> getAllDrugs(Integer page, Integer limit) {
        log.info("Get all drugs request: page: " + page + " limit: " + limit);
        Page<Drug> response = drugServiceImpl.getPageDrugs(Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(10));
        log.info("Get all drugs response: " + response);
        return response;
    }

    @GetMapping("/allDrugForUser")
    public List<UserDrugWithQuantityDto> getAllDrugForUser() {
        log.info("Get all user drugs request");
        List<UserDrugWithQuantityDto> response = drugServiceImpl.getAllDrugsForUser();
        log.info("Get all user drugs response: " + response);
        return response;
    }

    @GetMapping("/classification")
    public List<ClassificationDto> getClassification() {
        log.info("Get classification request");
        List<ClassificationDto> response = drugServiceImpl.getAllClassifications();
        log.info("Get classification response: " + response);
        return response;
    }

    @GetMapping(path = "/classification/{classificationId}", params = {"page", "limit"})
    public Page<Drug> getDrugsClassification(@PathVariable(value = "classificationId") Integer id, Integer page, Integer limit) {
        log.info("Get drugs by classification request: page: " + page + " limit: " + limit);
        Page<Drug> response = drugServiceImpl.getPageDrugsClassification(id, Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(10));
        log.info("Get drugs by classification response: " + response);
        return response;
    }


    @GetMapping("/filter/{drugName}")
    public List<DrugDto> filterDrugs(@PathVariable String drugName) {
        log.info("Get drugs by name request: " + drugName);
        List<DrugDto> response = drugServiceImpl.findByNameDrugs(drugName);
        log.info("Get drugs by name response: " + response);
        return response;
    }

    @PostMapping("/add/{drugId}")
    public DrugQuantityDto addDrugUser(@PathVariable Integer drugId, DrugQuantityDto drugQuantityDto) {
        log.info("Add new drug to user request: id drug: " + drugId + " quantity and expiration date " + drugQuantityDto);
        DrugQuantityDto response = drugServiceImpl.addDrugToUser(drugId, drugQuantityDto.getQuantity(), drugQuantityDto.getExpirationDate());
        log.info("Add new drug to user response: " + response);
        return response;
    }

    @PutMapping("/take/{userDrugQuantityId}")
    public DrugQuantityDto takeDrugUser(@PathVariable Integer userDrugQuantityId, Integer quantityDrugTaken) {
        log.info("Set a new quantity for the drug request: id userDrugQuantity: " + userDrugQuantityId + " quantity drug taken " + quantityDrugTaken);
        DrugQuantityDto response = drugServiceImpl.changeQuantity(userDrugQuantityId, quantityDrugTaken);
        log.info("Set a new quantity for the drug response: " + response);
        return response;
    }
}
