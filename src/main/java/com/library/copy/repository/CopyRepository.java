package com.library.copy.repository;

import com.library.books.domain.Book;
import com.library.copy.domain.Copy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CopyRepository extends CrudRepository<Copy,Long> {

    List<Copy> findAll();
    Optional<Copy> findById(Long id);
    Copy save(Copy copy);
    void deleteById(Long id);
    List<Copy> findByBookAndStatus(Book book, String status);

}
