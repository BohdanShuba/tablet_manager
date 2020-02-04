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
import org.springframework.http.ResponseEntity;
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
    public Page<Drug> getAllDrugs(Integer page, Integer size) {
        return drugServiceImpl.getPageDrugs(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
    }

    @GetMapping("/allDrugForUser")
    public List<UserDrugWithQuantityDto> getAllDrugForUser() {
        return drugServiceImpl.getAllDrugsForUser();
    }

    @GetMapping("/classification")
    public List<ClassificationDto> getClassification() {
        return drugServiceImpl.getAllClassifications();
    }

    @GetMapping(path = "/classification/{classificationId}", params = {"page", "size"})
    public Page<Drug> getDrugsClassification(Integer page, Integer size, @PathVariable(value = "classificationId") Integer id) {
        return drugServiceImpl.getPageDrugsClassification(id, Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
    }

    @GetMapping("/filter/{drugName}")
    public List<DrugDto> filterDrugs(@PathVariable String drugName) {
        return drugServiceImpl.findByNameDrugs(drugName);
    }

    @PostMapping("/add/{drugId}")
    public ResponseEntity<Void> addDrugUser(@PathVariable Integer drugId, DrugQuantityDto drugQuantityDto) {
        drugServiceImpl.addDrugToUser(drugId, drugQuantityDto.getQuantity(), drugQuantityDto.getExpirationDate());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/take/{userDrugQuantityId}")
    public ResponseEntity<Void> takeDrugUser(@PathVariable Integer userDrugQuantityId, Integer quantityDrugTaken) {
        drugServiceImpl.changeQuantity(userDrugQuantityId, quantityDrugTaken);
        return ResponseEntity.ok().build();
    }
}
