package org.domahaiev.bankapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domahaiev.bankapp.dto.CreateAgreementPersistDTO;
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
class AgreementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DirtiesContext
    void createAgreement() throws Exception {
        CreateAgreementPersistDTO createAgreementPersistDTO = new CreateAgreementPersistDTO();

        createAgreementPersistDTO.setAccountId("12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0");
        createAgreementPersistDTO.setSum("1000");
        createAgreementPersistDTO.setInterestRate("10");
        createAgreementPersistDTO.setProductId("b3f6d9e1-4c2a-4e8b-9a7e-6c8d5f3a1e9c");

        String capAgreementDTO = objectMapper.writeValueAsString(createAgreementPersistDTO);

        MvcResult createAgreementMvc = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/agreements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(capAgreementDTO))
                .andReturn();

        Assertions.assertEquals("agreement successfully created", createAgreementMvc.getResponse().getContentAsString());
        Assertions.assertEquals(201, createAgreementMvc.getResponse().getStatus());
    }

    @Test
    @DirtiesContext
    void terminateAgreement() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/agreements/terminate/e7b9c46b-12d5-41eb-b0e4-bc5b77c44a56")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("agreement successfully deactivated", mvcResult.getResponse().getContentAsString());
    }
}