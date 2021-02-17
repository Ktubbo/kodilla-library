package com.library.books.controller;

import com.library.books.domain.Book;
import com.library.service.DBService;
import com.library.books.domain.BookDto;
import com.library.books.domain.BookNotFoundException;
import com.library.books.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final DBService dbService;
    private final BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        List<Book> books = dbService.getAllBooks();
        return bookMapper.mapToBookDtoList(books);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(dbService.getBookById(bookId).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) { dbService.deleteBook(bookId);}

    @RequestMapping(method = RequestMethod.PUT, value = "updateBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = dbService.saveBook(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        dbService.saveBook(book);
    }

}
