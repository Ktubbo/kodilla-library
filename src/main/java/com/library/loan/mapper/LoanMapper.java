package com.library.loan.mapper;

import com.library.loan.domain.Loan;
import com.library.loan.domain.LoanDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanMapper {

    public Loan mapToLoan(LoanDto loanDto) {
        return new Loan(
                loanDto.getId(),
                loanDto.getCopy(),
                loanDto.getUser(),
                loanDto.getBorrowingDate(),
                loanDto.getReturningDate()
        );
    }

    public LoanDto mapToLoanDto(Loan loan) {
        return new LoanDto(
                loan.getId(),
                loan.getCopy(),
                loan.getUser(),
                loan.getBorrowingDate(),
                loan.getReturningDate()
        );
    }

    public List<LoanDto> mapToLoanDtoList(List<Loan> loansList) {
        return loansList.stream()
                .map(this::mapToLoanDto)
                .collect(Collectors.toList());
    }
}
