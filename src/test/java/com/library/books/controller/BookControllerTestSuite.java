package com.library.books.controller;

import com.library.books.domain.Book;
import com.library.books.domain.BookDto;
import com.library.books.domain.BookNotFoundException;
import com.library.books.mapper.BookMapper;
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
class BookControllerTestSuite {

    @Autowired
    private BookController bookController;
    @Autowired
    private BookMapper bookMapper;

    @Test
    void testGetBooks() {
        //Given
        Book book = new Book("Nice book", "Super author", 2010);
        //When
        bookController.createBook(bookMapper.mapToBookDto(book));
        List<BookDto> bookDtoList = bookController.getBooks();
        //Then
        assertEquals(1, bookDtoList.size());
    }

    @Test
    void testDeleteBook() {
        //Given
        Book book = new Book("Nice book", "Super author", 2010);
        BookDto bookDto = bookMapper.mapToBookDto(book);
        //When
        bookController.createBook(bookDto);
        final long bookId = bookController.getBooks().get(0).getId();
        bookController.deleteBook(bookId);
        //Then
        assertThrows(BookNotFoundException.class,() -> {
            bookController.getBook(bookId);});
    }

    @Test
    void testUpdateBook() {
        //Given
        BookDto bookDtoUpdated = new BookDto(1L,"Updated Nice book", "Updated Super author", 2015);
        //When
        bookController.updateBook(bookDtoUpdated);
        final long bookId = bookController.getBooks().get(0).getId();
        //Then
        try {
            BookDto resultBookDto = bookController.getBook(bookId);
            assertEquals("Updated Nice book", resultBookDto.getTitle());
        } catch (Exception e) {
            fail();
        }
    }

}