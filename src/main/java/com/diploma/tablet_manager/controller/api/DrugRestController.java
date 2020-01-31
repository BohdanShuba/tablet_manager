package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.dto.UserDrugWithQuantityDto;
import com.diploma.tablet_manager.service.impl.DrugServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugRestController {
    private final DrugServiceImpl drugServiceImpl;

    @GetMapping("/getAllDrugForUser")
    public List<UserDrugWithQuantityDto> getAllDrugForUser() {
        List<UserDrugWithQuantityDto> response = drugServiceImpl.getAllDrugsForUser();
        return response;
    }
}
