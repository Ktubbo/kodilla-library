package com.library.loan.controller;

import com.library.books.domain.Book;
import com.library.books.mapper.BookMapper;
import com.library.copy.domain.Copy;
import com.library.loan.controller.LoanController;
import com.library.loan.domain.Loan;
import com.library.loan.domain.LoanDto;
import com.library.loan.domain.LoanNotFoundException;
import com.library.loan.mapper.LoanMapper;
import com.library.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class LoanControllerTest {

    @Autowired
    private LoanController loanController;
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private BookMapper bookMapper;


    @Test
    void testGetLoans() {
        //Given
        Book book = new Book("Super Book", "Super Author", 2013);
        Copy copy = new Copy(book, "Available");
        User user = new User("Marcin","Kuryła");
        Loan loan = new Loan(copy,user);
        //When
        loanController.borrowBook(loanMapper.mapToLoanDto(loan));
        List<LoanDto> loanDtoList = loanController.getLoans();
        long loanId = loanDtoList.get(0).getId();
        //Then
        assertEquals(1, loanDtoList.size());

        try {
            assertEquals("Super Book", loanController.getLoan(loanId).getCopy().getBook().getTitle());
            assertEquals("Super Author", loanController.getLoan(loanId).getCopy().getBook().getAuthor());
            assertEquals("Borrowed", loanController.getLoan(loanId).getCopy().getStatus());
            assertEquals("Marcin", loanController.getLoan(loanId).getUser().getFirstName());
            assertEquals("Kuryła", loanController.getLoan(loanId).getUser().getLastName());
            assertNotNull(loanController.getLoan(loanId).getBorrowingDate());
            assertNull(loanController.getLoan(loanId).getReturningDate());
        } catch (LoanNotFoundException e) {
            fail();
        }
    }

    @Test
    void testDeleteLoan() {
        //Given
        Book book = new Book("Super Book", "Super Author", 2013);
        Copy copy = new Copy(book, "Borrowed");
        User user = new User("Marcin","Kuryła");
        Loan loan = new Loan(copy,user);
        //When
        loanController.borrowBook(loanMapper.mapToLoanDto(loan));
        List<LoanDto> loanDtoList = loanController.getLoans();
        long loanId = loanDtoList.get(0).getId();
        //When
        loanController.deleteLoan(loanId);
        //Then
        assertThrows(LoanNotFoundException.class, () -> {
            loanController.getLoan(loanId);
        });
    }

    @Test
    void testReturnBook() {
        //Given
        Book book = new Book("Super Book", "Super Author", 2013);
        Copy copy = new Copy(book, "Borrowed");
        User user = new User("Marcin","Kuryła");
        Loan loan = new Loan(copy,user);
        //When
        loanController.borrowBook(loanMapper.mapToLoanDto(loan));
        long loanId = loanController.getLoans().get(0).getId();
        LoanDto loanDto = loanController.returnBook(loanMapper.mapToLoanDto(loan));
        //Then
        try {
            assertEquals("Available", loanController.getLoan(loanId).getCopy().getStatus());
            assertNotNull(loanController.getLoan(loanId).getBorrowingDate());
            assertNotNull(loanDto.getReturningDate());
        } catch (LoanNotFoundException e) {
            fail();
        }
    }
}