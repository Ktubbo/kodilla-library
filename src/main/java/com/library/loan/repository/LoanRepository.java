package com.library.loan.repository;

import com.library.loan.domain.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan,Long> {

    List<Loan> findAll();
    Optional<Loan> findById(Long id);
    Loan save(Loan loan);
    void deleteById(Long id);

}
