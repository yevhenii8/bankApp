package org.domahaiev.bankapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domahaiev.bankapp.dto.CreateTransactionPersistDTO;
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
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DirtiesContext
    void createTransaction() throws Exception{

        CreateTransactionPersistDTO createTransactionPersistDTO = new CreateTransactionPersistDTO();

        createTransactionPersistDTO.setAmount("1000");
        createTransactionPersistDTO.setAccountId("12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0");
        createTransactionPersistDTO.setTransactionTypeStatus("DEPOSIT");
        createTransactionPersistDTO.setDescription("testDescription");

        String transactionDTOJSON = objectMapper.writeValueAsString(createTransactionPersistDTO);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionDTOJSON))
                .andReturn();

        Assertions.assertEquals("transaction successfully created", mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }
}