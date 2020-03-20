package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.domain.Drug;
import com.diploma.tablet_manager.dto.ClassificationDto;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.dto.DrugQuantityDto;
import com.diploma.tablet_manager.dto.UserDrugWithQuantityDto;
import com.diploma.tablet_manager.service.DrugService;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "User drug management", description = "Operations pertaining with user drugs")
public class DrugRestController {

    private final DrugService drugService;

    @ApiOperation(value = "View page of all drugs", response = Page.class)
    @GetMapping(params = {"page", "limit"})
    public Page<Drug> getAllDrugs(
            @ApiParam(value = "Results page you want to retrieve (0..N)") Integer page,
            @ApiParam(value = "Number of records per page") Integer limit) {
        log.info("Get all drugs request: page: " + page + " limit: " + limit);
        Page<Drug> response = drugService.getPageDrugs(Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(10));
        log.info("Get all drugs response: " + response);
        return response;
    }

    @ApiOperation(value = "View a list of user drugs", response = List.class)
    @GetMapping("/allDrugForUser")
    public List<UserDrugWithQuantityDto> getAllDrugForUser() {
        log.info("Get all user drugs request");
        List<UserDrugWithQuantityDto> response = drugService.getAllDrugsForUser();
        log.info("Get all user drugs response: " + response);
        return response;
    }

    @ApiOperation(value = "View a list of classification", response = List.class)
    @GetMapping("/classification")
    public List<ClassificationDto> getClassification() {
        log.info("Get classification request");
        List<ClassificationDto> response = drugService.getAllClassifications();
        log.info("Get classification response: " + response);
        return response;
    }

    @ApiOperation(value = "View a list drugs from the classification", response = Page.class)
    @GetMapping(path = "/classification/{classificationId}", params = {"page", "limit"})
    public Page<Drug> getDrugsClassification(
            @ApiParam(value = "Classification Id to view drugs from the category") @PathVariable(value = "classificationId") Integer id,
            @ApiParam(value = "Results page you want to retrieve (0..N)") Integer page,
            @ApiParam(value = "Number of records per page") Integer limit) {
        log.info("Get drugs by classification request: page: " + page + " limit: " + limit);
        Page<Drug> response = drugService.getPageDrugsClassification(id, Optional.ofNullable(page).orElse(0), Optional.ofNullable(limit).orElse(10));
        log.info("Get drugs by classification response: " + response);
        return response;
    }

    @ApiOperation(value = "View a list of drugs found", response = List.class)
    @GetMapping("/filter/{drugName}")
    public List<DrugDto> filterDrugs(@ApiParam(value = "Name for the drug search") @PathVariable String drugName) {
        log.info("Get drugs by name request: " + drugName);
        List<DrugDto> response = drugService.findByNameDrugs(drugName);
        log.info("Get drugs by name response: " + response);
        return response;
    }

    @ApiOperation(value = "Add tablet to user", response = DrugQuantityDto.class)
    @PostMapping("/add/{drugId}")
    public DrugQuantityDto addDrugUser(
            @ApiParam(value = "Drug Id to add  user drug") @PathVariable Integer drugId,
            @ApiParam(value = "UserDrugQuantity object store in database table") @RequestBody DrugQuantityDto drugQuantityDto) {
        log.info("Add new drug to user request: id drug: " + drugId + " quantity and expiration date " + drugQuantityDto);
        DrugQuantityDto response = drugService.addDrugToUser(drugId, drugQuantityDto.getQuantity(), drugQuantityDto.getExpirationDate());
        log.info("Add new drug to user response: " + response);
        return response;
    }

    @ApiOperation(value = "Update number of user tablets", response = DrugQuantityDto.class)
    @PutMapping("/take/{userDrugQuantityId}")
    public DrugQuantityDto takeDrugUser(@ApiParam(value = "UserDrugQuantity Id to update the quantity") @PathVariable Integer userDrugQuantityId, Integer quantityDrugTaken) {
        log.info("Set a new quantity for the drug request: id userDrugQuantity: " + userDrugQuantityId + " quantity drug taken " + quantityDrugTaken);
        DrugQuantityDto response = drugService.changeQuantity(userDrugQuantityId, quantityDrugTaken);
        log.info("Set a new quantity for the drug response: " + response);
        return response;
    }
}
