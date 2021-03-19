package com.library.study.demo.controller;

import com.library.study.demo.domain.BorrowedBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BorrowedBookDTO {
    private Long id;
    private Long bookId;
    private String title;
    private String userId;
    private Date bDate;

    public BorrowedBookDTO(BorrowedBook borrowedBook) {
        this.id = borrowedBook.getId();
        this.bookId = borrowedBook.getBook().getId();
        this.title = borrowedBook.getBook().getTitle();
        this.userId = borrowedBook.getUser().getId();
        this.bDate = borrowedBook.getBDate();
    }


}
