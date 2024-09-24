package org.domahaiev.bankapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domahaiev.bankapp.dto.CreateManagerPersistDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/createTestDB.sql")
@Sql("/addTestData.sql")
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DirtiesContext
    void createManager() throws Exception {

        CreateManagerPersistDTO createManagerPersistDTO = new CreateManagerPersistDTO();

        createManagerPersistDTO.setFirstName("testFirstName");
        createManagerPersistDTO.setLastName("testLastName");
        createManagerPersistDTO.setSocialSecurityNumber("123-54-6789");

        String managerDTOJSON = objectMapper.writeValueAsString(createManagerPersistDTO);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/managers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(managerDTOJSON))
                .andReturn();

        Assertions.assertEquals("manager successfully created", mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    @DirtiesContext
    void deactivateManager() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/managers/deactivate/6e5a9d7b-8c4f-4d1b-a3e9-5f2c7a8b1d3e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("manager successfully deactivated", mvcResult.getResponse().getContentAsString() );
    }
}