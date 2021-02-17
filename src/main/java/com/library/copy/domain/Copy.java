package com.library.copy.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.books.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="copies")
@Component
public class Copy {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "bookId")
    @JsonProperty("bookId")
    private Book book;

    @Column(name = "status")
    private String status;

    public Copy(Book book, String status) {
        this.book = book;
        this.status = status;
    }
}
