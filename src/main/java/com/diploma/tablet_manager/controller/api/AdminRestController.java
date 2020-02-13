package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.service.DrugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Api(value = "Administration", description = "Operations pertaining to administration")
public class AdminRestController {

    private final DrugService drugServiceImpl;

    @ApiOperation(value = "Add new drug", response = DrugDto.class)
    @PostMapping
    public DrugDto add(@ApiParam(value = "Drug object store in database table") @RequestBody DrugDto drugDto) {
        log.info("Add drug request: " + drugDto);
        DrugDto response = drugServiceImpl.addNewDrug(drugDto);
        log.info("Add drug response: " + response);
        return response;
    }
}
