package com.library.study.demo.domain;

import com.library.study.demo.controller.BookDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @ColumnDefault("false")
    private boolean isBorrowed;

    @ManyToOne
    private Library library;

    public Book(String title, String author, Library library) {
        this.title = title;
        this.author = author;
        this.library = library;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(BookDTO bookDTO) {
        if(bookDTO.getId() != null){
            this.id = bookDTO.getId();
        }
        this.title = bookDTO.getTitle();
        this.author = bookDTO.getAuthor();
        this.isBorrowed = bookDTO.isBorrowed();
    }
}
