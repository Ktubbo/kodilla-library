package com.library.books.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="books")
@Component
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publishingDate")
    private int publishingDate;

    public Book(String title, String author, int publishingDate) {
        this.title = title;
        this.author = author;
        this.publishingDate = publishingDate;
    }
}
