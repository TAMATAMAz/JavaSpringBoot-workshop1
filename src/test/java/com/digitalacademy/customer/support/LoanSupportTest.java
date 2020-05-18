package com.digitalacademy.customer.support;

import com.digitalacademy.customer.model.response.GetLoanInfoResponse;

public class LoanSupportTest {
    public static GetLoanInfoResponse getLoanInfo() {
        GetLoanInfoResponse getLoanInfoResponse = new GetLoanInfoResponse();
        getLoanInfoResponse.setId(1L);
        getLoanInfoResponse.setStatus("OK");
        getLoanInfoResponse.setAccountPayable("102-222-222");
        getLoanInfoResponse.setAccountReceivable("111-111-111");
        getLoanInfoResponse.setPrincipalAmount(100.0);
        return getLoanInfoResponse;
    }
}
