package com.library.copy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.books.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyDto {
    private Long id;
    @JsonProperty("bookId")
    private Book book;
    private String status;
}
