package com.library.loan.controller;

import com.library.loan.domain.Loan;
import com.library.loan.domain.LoanDto;
import com.library.loan.domain.LoanNotFoundException;
import com.library.loan.mapper.LoanMapper;
import com.library.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoanController {

    private final DBService dbService;
    private final LoanMapper loanMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getLoans")
    public List<LoanDto> getLoans() {
        List<Loan> loans = dbService.getAllLoans();
        return loanMapper.mapToLoanDtoList(loans);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getLoan")
    public LoanDto getLoan(@RequestParam Long loanId) throws LoanNotFoundException {
        return loanMapper.mapToLoanDto(dbService.getLoanById(loanId).orElseThrow(LoanNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteLoan")
    public void deleteLoan(@RequestParam Long loanId) { dbService.deleteLoan(loanId);}

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoanDto returnBook(@RequestBody LoanDto loanDto) {
        Loan loan = loanMapper.mapToLoan(loanDto);
        loan.getCopy().setStatus("Available");
        loan.setReturningDate(Date.valueOf(LocalDate.now()));
        Loan savedLoan = dbService.saveLoan(loan);
        return loanMapper.mapToLoanDto(savedLoan);
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody LoanDto loanDto) {
        Loan loan = loanMapper.mapToLoan(loanDto);
        loan.getCopy().setStatus("Borrowed");
        loan.setBorrowingDate(Date.valueOf(LocalDate.now()));
        dbService.saveLoan(loan);
    }

}
