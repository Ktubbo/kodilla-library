package com.library.service;

import com.library.books.domain.Book;
import com.library.copy.domain.Copy;
import com.library.copy.repository.CopyRepository;
import com.library.books.repository.BookRepository;
import com.library.loan.domain.Loan;
import com.library.loan.repository.LoanRepository;
import com.library.user.domain.User;
import com.library.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;
    private final LoanRepository loanRepository;

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }
    public User saveUser(final User user) { return userRepository.save(user); }
    public void deleteUser(Long id) { userRepository.deleteById(id); }

    public List<Book> getAllBooks() { return bookRepository.findAll(); }
    public Optional<Book> getBookById(Long id) { return bookRepository.findById(id); }
    public Book saveBook(final Book book) { return bookRepository.save(book); }
    public void deleteBook(Long id) { bookRepository.deleteById(id); }

    public List<Copy> getAllCopies() { return copyRepository.findAll(); }
    public Optional<Copy> getCopyById(Long id) { return copyRepository.findById(id); }
    public Copy saveCopy(final Copy copy) { return copyRepository.save(copy); }
    public void deleteCopy(Long id) { copyRepository.deleteById(id); }
    public List<Copy> countAvailableCopies(final Book book) { return copyRepository.findByBookAndStatus(book,"Available"); }

    public List<Loan> getAllLoans() { return loanRepository.findAll(); }
    public Optional<Loan> getLoanById(Long id) { return loanRepository.findById(id); }
    public Loan saveLoan(final Loan loan) { return  loanRepository.save(loan); }
    public void deleteLoan(Long id) { loanRepository.deleteById(id); }
}
