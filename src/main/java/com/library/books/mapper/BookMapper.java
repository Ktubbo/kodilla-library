package com.library.books.mapper;

import com.library.books.domain.Book;
import com.library.books.domain.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublishingDate()
        );
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishingDate()
        );
    }

    public List<BookDto> mapToBookDtoList(List<Book> titlesList) {
        return titlesList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

}
