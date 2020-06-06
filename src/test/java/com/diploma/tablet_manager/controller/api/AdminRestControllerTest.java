package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.service.DrugService;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = AdminRestController.class, secure = false)
public class AdminRestControllerTest {
//    @Autowired
//    private MockMvc mockMvc;

//    String exampleDrugDtoJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";
//    @MockBean
//    private DrugService drugService;
//
//    public void createStudentCourse() throws Exception {
//        DrugDto mockDrugDto = new DrugDto("Аспирин", "https://tabletki.ua/%d0%90%d1%81%d0%bf%d0%b8%d1%80%d0%b8%d0%bd/", 10);
//        Mockito.when(drugService.addNewDrug(Mockito.any(DrugDto.class))).thenReturn(mockDrugDto);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/api/admin")
//                .accept(MediaType.APPLICATION_JSON).content(exampleDrugDtoJson)
//                .contentType(MediaType.APPLICATION_JSON);
//    }
}