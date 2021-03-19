package com.library.study.demo.controller;

import com.library.study.demo.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Long libraryId;
    private boolean isBorrowed;

    public BookDTO(String title, String author, Long libraryId) {
        this.title = title;
        this.author = author;
        this.libraryId = libraryId;
    }

    public BookDTO(Long id, String title, String author, Long libraryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.libraryId = libraryId;
    }

    public BookDTO(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.libraryId = book.getLibrary().getId();
        this.isBorrowed = book.isBorrowed();
    }


}
