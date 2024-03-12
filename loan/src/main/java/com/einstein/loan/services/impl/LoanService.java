package com.einstein.loan.services.impl;

import com.einstein.loan.clients.CardFeignClient;
import com.einstein.loan.dtos.*;
import com.einstein.loan.exceptions.LoanAlreadyExistException;
import com.einstein.loan.exceptions.ResourceNotFoundException;
import com.einstein.loan.mappers.LoanMapper;
import com.einstein.loan.models.Loan;
import com.einstein.loan.repositories.LoanRepository;
import com.einstein.loan.services.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;
    private final CardFeignClient cardClient;

    @Override
    public ConsultLoanOutput consultLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        return LoanMapper.toConsultLoanOutput(loan);
    }

    @Override
    public void createLoan(CreateLoanInput dto) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(dto.getMobileNumber());
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistException("Loan already exists");
        }
        Loan loan = LoanMapper.toLoan(dto);
        loanRepository.save(loan);
    }

    @Override
    public boolean updateLoan(UpdateLoanInput dto) {
        Loan loan = loanRepository.findByMobileNumber(dto.getMobileNumber()).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", dto.getMobileNumber()));
        LoanMapper.updateLoan(loan, dto);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        loanRepository.delete(loan);
        return true;
    }

    @Override
    public ConsultCardAndLoanOutput consultCardAndLoan(String correlationId, String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        ConsultLoanOutput loanOutput = LoanMapper.toConsultLoanOutput(loan);
        ResponseEntity<ConsultCardOutput> consultCardOutputResponseEntity = cardClient.consultCard(correlationId, mobileNumber);
        ConsultCardOutput cardOutput = null;
        if (null != consultCardOutputResponseEntity) {
            cardOutput = consultCardOutputResponseEntity.getBody();
        }
        return new ConsultCardAndLoanOutput(cardOutput, loanOutput);
    }
}
