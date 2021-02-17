package com.library.books.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private int publishingDate;
}
