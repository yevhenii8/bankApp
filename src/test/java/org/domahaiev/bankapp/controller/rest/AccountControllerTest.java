package org.domahaiev.bankapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.domahaiev.bankapp.dto.CreateAccountPersistDTO;
import org.domahaiev.bankapp.model.Account;
import org.domahaiev.bankapp.model.enums.AccountCurrency;
import org.domahaiev.bankapp.model.enums.AccountType;
import org.domahaiev.bankapp.model.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.sql.Timestamp;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/createTestDB.sql")
@Sql("/addTestData.sql")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void createAccountTest() throws Exception {

        CreateAccountPersistDTO createAccountPersistDTO = new CreateAccountPersistDTO();

        createAccountPersistDTO.setUsername("testUsername");
        createAccountPersistDTO.setPassword("testPassword");
        createAccountPersistDTO.setAccountType("ONLINE_BANKING");
        createAccountPersistDTO.setAccountCurrency("USD");
        createAccountPersistDTO.setClientId("f95a4c9a-3a5b-4c22-8b56-73b2a3e831df");

        String capAccountDTO = objectMapper.writeValueAsString(createAccountPersistDTO);

        MvcResult createAccountResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(capAccountDTO))
                .andReturn();

        Assertions.assertEquals(HttpStatus.CREATED.value(), createAccountResult.getResponse().getStatus());
        Assertions.assertEquals("account successfully created", createAccountResult.getResponse().getContentAsString());
    }

    @Test
    @DirtiesContext
    void deactivateAccountById() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/accounts/deactivate/12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals("account successfully deactivated", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DirtiesContext
    void getAccountById() throws Exception {

        Account account = new Account();
        account.setId(UUID.fromString("12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0"));
        account.setUsername("Account 1");
        account.setPassword("R8jvP3wz!DqL");
        account.setAccountType(AccountType.ONLINE_BANKING);
        account.setAccountStatus(Status.ACTIVE);
        account.setBalance(1000);
        account.setAccountCurrency(AccountCurrency.USD);
        account.setCreatedAt(Timestamp.valueOf("2024-07-29 00:00:00"));
        account.setUpdatedAt(Timestamp.valueOf("2024-07-29 00:00:00"));

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/accounts/id/12b5e6d8-2ae0-4d09-b35c-6e6b540b7cf0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String accountJson = mvcResult.getResponse().getContentAsString();
        Account accountResponse = objectMapper.readValue(accountJson, Account.class);

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals(account.getId(), accountResponse.getId());
        Assertions.assertEquals(account.getUsername(), accountResponse.getUsername());
        Assertions.assertEquals(account.getPassword(), accountResponse.getPassword());
        Assertions.assertEquals(account.getAccountType(), accountResponse.getAccountType());
        Assertions.assertEquals(account.getAccountStatus(), accountResponse.getAccountStatus());
        Assertions.assertEquals(account.getBalance(), accountResponse.getBalance());
        Assertions.assertEquals(account.getAccountCurrency(), accountResponse.getAccountCurrency());
        Assertions.assertEquals(account.getCreatedAt(), accountResponse.getCreatedAt());
        Assertions.assertEquals(account.getUpdatedAt(), accountResponse.getUpdatedAt());
    }
}