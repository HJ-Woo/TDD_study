package com.library.study.demo.repository;

import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.BorrowedBook;
import com.library.study.demo.domain.Library;
import com.library.study.demo.domain.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BorrowedBookRepositoryTest {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    private Book book;
    private Member member;
    private Library library;
    private BorrowedBook borrowedBook;
    private Date saveDate;

    @BeforeAll
    public void setUp() {
        library = new Library();
        libraryRepository.save(library);

        book = new Book("ABC", "ispnss", library);
        member = new Member("nosappq22","qqrr112","dlauddms");

        bookRepository.save(book);
        memberRepository.save(member);

        saveDate = new Date();
        borrowedBook = new BorrowedBook(book, member, saveDate);
    }

    @Test
    public void saveBorrwedBookTest() {
        BorrowedBook saveBorrowedBook = borrowedBookRepository.save(borrowedBook);
        assertThat(saveBorrowedBook.getBook().getTitle(), is(book.getTitle()));
        assertThat(saveBorrowedBook.getMember().getPassword(), is(member.getPassword()));
        assertThat(saveBorrowedBook.getBDate(), is(saveDate));
    }

    @Test
    void updateBorrwedBookTest(){
        BorrowedBook updateBorrowedBook = borrowedBookRepository.save(borrowedBook);
        Book book2 = new Book("OOPP", "llle", library);
        Member member2 = new Member("adi2221", "oosss23", "oaopsk");
        borrowedBook.setBook(book2);
        borrowedBook.setMember(member2);

        BorrowedBook findBorrowedBook = borrowedBookRepository.findById(updateBorrowedBook.getId()).orElse(null);
        assertThat(findBorrowedBook.getBook().getTitle(), is(book2.getTitle()));
        assertThat(findBorrowedBook.getMember().getPassword(), is(member2.getPassword()));
    }

    @Test
    void deleteBorrwedBookTest() {
        BorrowedBook saveBook = borrowedBookRepository.save(borrowedBook);
        borrowedBookRepository.deleteById(saveBook.getId());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    BorrowedBook findBorrowedBook = borrowedBookRepository.findById(saveBook.getId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 아이디입니다."));
                }).withMessageMatching("찾을 수 없는 아이디입니다.");
    }

}
