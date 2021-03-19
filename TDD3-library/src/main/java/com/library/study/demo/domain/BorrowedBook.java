package com.library.study.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BorrowedBook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Book book;
    @OneToOne
    private User user;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date bDate;

    public BorrowedBook(Book book, User user, Date bDate) {
        this.book = book;
        this.user = user;
        this.bDate = bDate;
    }
}
