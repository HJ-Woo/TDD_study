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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
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
    private Book book2;
    private Member member;
    private Library library;
    private BorrowedBook borrowedBook;
    private BorrowedBook borrowedBook2;
    private Date saveDate;

    @BeforeAll
    public void setUp() {
        library = new Library();
        libraryRepository.save(library);

        book = new Book("ABC", "ispnss", library);
        book2 = new Book("ABC2", "ispnss2", library);
        member = new Member("nosappq22","qqrr112","dlauddms");


        bookRepository.save(book);
        bookRepository.save(book2);
        memberRepository.save(member);

        saveDate = new Date();
        borrowedBook = new BorrowedBook(book, member, saveDate);
        borrowedBook2 = new BorrowedBook(book2, member, saveDate);
    }

    @Test
    public void saveBorrwedBookTest() {
        BorrowedBook saveBorrowedBook = borrowedBookRepository.save(borrowedBook);
        assertThat(saveBorrowedBook.getBook().getTitle(), is(book.getTitle()));
        assertThat(saveBorrowedBook.getUser().getPassword(), is(member.getPassword()));
        assertThat(saveBorrowedBook.getBDate(), is(saveDate));
    }

    @Test
    void updateBorrwedBookTest(){
        BorrowedBook updateBorrowedBook = borrowedBookRepository.save(borrowedBook);
        Book book2 = new Book("OOPP", "llle", library);
        Member member2 = new Member("adi2221", "oosss23", "oaopsk");
        updateBorrowedBook.setBook(book2);
        updateBorrowedBook.setUser(member2);

        BorrowedBook findBorrowedBook = borrowedBookRepository.findById(updateBorrowedBook.getId()).orElse(null);
        assertThat(findBorrowedBook.getBook().getTitle(), is(book2.getTitle()));
        assertThat(findBorrowedBook.getUser().getPassword(), is(member2.getPassword()));
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

    @Test
    void findByUserIdTest(){
        BorrowedBook saveBorrowedBook = borrowedBookRepository.save(borrowedBook);
        BorrowedBook saveBorrowedBook2 = borrowedBookRepository.save(borrowedBook2);
        List<BorrowedBook> borrowedBookList = borrowedBookRepository.findByUserId(member.getId());
        assertThat(borrowedBookList.size(), is(2));

        List<BorrowedBook> failBorrowedBooks = borrowedBookRepository.findByUserId("test");
        assertThat(failBorrowedBooks.size(), is(0));
    }


}
