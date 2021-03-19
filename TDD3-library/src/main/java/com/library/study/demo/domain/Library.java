package com.library.study.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Library {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Book> bookList; // 가지고 있는 책 list
    @OneToMany
    private List<Book> borrowedBookList; // 빌려준 책 list



}
