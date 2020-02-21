package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.service.DrugService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminRestController.class, secure = false)
public class AdminRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DrugService drugServiceImpl;

}
