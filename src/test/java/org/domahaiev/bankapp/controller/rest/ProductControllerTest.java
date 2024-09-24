package org.domahaiev.bankapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domahaiev.bankapp.dto.CreateProductPersistDTO;
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
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DirtiesContext
    void createProduct() throws Exception {

        CreateProductPersistDTO createProductPersistDTO = new CreateProductPersistDTO();

        createProductPersistDTO.setProductName("MORTGAGE");
        createProductPersistDTO.setProductLimit("1000");
        createProductPersistDTO.setInterestRate("10");

        String productDTOJSON = objectMapper.writeValueAsString(createProductPersistDTO);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productDTOJSON))
                .andReturn();

        Assertions.assertEquals("product successfully created", mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    @DirtiesContext
    void deactivateProduct() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/products/deactivate/b3f6d9e1-4c2a-4e8b-9a7e-6c8d5f3a1e9c")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals("product successfully deactivated", mvcResult.getResponse().getContentAsString());
    }
}