package com.einstein.loan.services;

import com.einstein.loan.dtos.ConsultLoanOutput;
import com.einstein.loan.dtos.CreateLoanInput;
import com.einstein.loan.dtos.UpdateLoanInput;

public interface ILoanService {

    ConsultLoanOutput consultLoan(String mobileNumber);
    void createLoan(CreateLoanInput dto);
    boolean updateLoan(UpdateLoanInput dto);
    boolean deleteLoan(String mobileNumber);
}
