package org.domahaiev.bankapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domahaiev.bankapp.dto.CreateClientPersistDTO;
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
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DirtiesContext
    void createClient() throws Exception {
        CreateClientPersistDTO createClientPersistDTO = new CreateClientPersistDTO();

        createClientPersistDTO.setFirstName("testFirstName");
        createClientPersistDTO.setLastName("testLastName");
        createClientPersistDTO.setEmail("testEmail");
        createClientPersistDTO.setAddress("testAddress");
        createClientPersistDTO.setPhone("testPhone");
        createClientPersistDTO.setSocialSecurityNumber("123-45-9876");
        createClientPersistDTO.setTaxCode("12345");

        String clientDTOJSON = objectMapper.writeValueAsString(createClientPersistDTO);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDTOJSON))
                .andReturn();

        Assertions.assertEquals("client successfully created", mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    @DirtiesContext
    void deactivateClientById() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/clients/deactivate/f95a4c9a-3a5b-4c22-8b56-73b2a3e831df")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("client successfully deactivated", mvcResult.getResponse().getContentAsString());
    }
}