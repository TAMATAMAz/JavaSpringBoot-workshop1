package com.digitalacademy.customer.controller;

import com.digitalacademy.customer.api.LoanApi;
import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.model.response.GetLoanInfoResponse;
import com.digitalacademy.customer.support.CustomerSupportTest;
import com.digitalacademy.customer.support.LoanSupportTest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.digitalacademy.customer.controller.CustomerControllerTest.urlCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanControllerTest {

    @Mock
    LoanApi loanApi;

    @InjectMocks
    LoanController loanController;

    private MockMvc mvc;

    //public static final String urlCustomerList = "/customer/list/";
    public static final String urlLoan = "/loan/";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanController = new LoanController(loanApi);
        mvc = MockMvcBuilders.standaloneSetup(loanController)
                .build();
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception{
        GetLoanInfoResponse getLoanInfoResponse ;
        Long reqId = 1L;

        when(loanApi.getLoanInfo(reqId)).thenReturn(LoanSupportTest.getLoanInfo());
        MvcResult mvcResult = mvc.perform(get(urlLoan + "/" + reqId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals("1",json.get("id").toString());
        Assert.assertEquals("OK",json.get("status"));
        Assert.assertEquals("102-222-222",json.get("account_payable"));
        Assert.assertEquals("111-111-111",json.get("account_receivable"));
        Assert.assertEquals(100.0,json.get("principal_amount"));

    }
}
