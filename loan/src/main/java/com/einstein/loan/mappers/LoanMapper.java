package com.einstein.loan.mappers;

import com.einstein.loan.dtos.ConsultLoanOutput;
import com.einstein.loan.dtos.CreateLoanInput;
import com.einstein.loan.dtos.UpdateLoanInput;
import com.einstein.loan.models.Loan;

public class LoanMapper {

    public static ConsultLoanOutput toConsultLoanOutput(Loan loan) {
        return ConsultLoanOutput.builder()
              .mobileNumber(loan.getMobileNumber())
              .loanNumber(loan.getLoanNumber())
              .loanType(loan.getLoanType())
              .totalLoan(loan.getTotalLoan())
              .amountPaid(loan.getAmountPaid())
              .outstandingAmount(loan.getOutstandingAmount())
              .build();
    }

    public static Loan toLoan(CreateLoanInput dto) {
        return Loan.builder()
                .mobileNumber(dto.getMobileNumber())
                .loanNumber(dto.getLoanNumber())
                .loanType(dto.getLoanType())
                .totalLoan(dto.getTotalLoan())
                .amountPaid(dto.getAmountPaid())
                .outstandingAmount(dto.getOutstandingAmount())
                .build();
    }

    public static void updateLoan(Loan loan, UpdateLoanInput dto) {
        loan.setMobileNumber(dto.getMobileNumber());
        loan.setLoanNumber(dto.getLoanNumber());
        loan.setLoanType(dto.getLoanType());
        loan.setTotalLoan(dto.getTotalLoan());
        loan.setAmountPaid(dto.getAmountPaid());
        loan.setOutstandingAmount(dto.getOutstandingAmount());
    }
}
