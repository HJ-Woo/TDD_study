package com.library.study.demo.service;


import com.library.study.demo.domain.*;
import com.library.study.demo.repository.BookRepository;
import com.library.study.demo.repository.BorrowedBookRepository;
import com.library.study.demo.repository.LibraryRepository;
import com.library.study.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BorrowedBookRepository borrowedBookRepository;

    @InjectMocks
    private BookService bookService;

    @Autowired
    private LibraryRepository testlibraryRepository;

    private Book book;
    private List<Book> bookList;
    private List<BorrowedBook> borrowedBookList;
    private Library library;
    private User member;
    private User admin;

    private static final Long LIBRARYID = 1L;

    @BeforeAll
    public void setUp() {
//        library = mock(Library.class);
//        Field idField = ReflectionUtils.findField(Library.class, "id");
//        idField.setAccessible(true);
//        ReflectionUtils.setField(idField, library, LIBRARYID);


        library = new Library();
        library = testlibraryRepository.save(library);

        book = new Book("bookTitle", "bookAuthor", library);

        bookList = new ArrayList<>();
        for(int i=0; i<5; i++){
            bookList.add(new Book("Tl"+i, "Au"+i, library));
        }

        borrowedBookList = new ArrayList<>();
        for(int i=0; i<5; i++){
            borrowedBookList.add(new BorrowedBook(bookList.get(i), member, new Date()));
        }

        member = new Member("id", "pwd", "name");
        admin = new Admin("id2", "pwd2", "name2");

        //enroll에서 사용
        when(libraryRepository.findById(LIBRARYID)).thenReturn(Optional.of(library));
        when(bookRepository.save(any())).thenReturn(book);
        //delete에서 사용
        //when(bookRepository.deleteById(book.getId()));
        //searchAll에서 사용
        when(bookRepository.findAll()).thenReturn(bookList);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(userRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
    }

    @Test
    public void enrollTest() {
        Book enrollBook = bookService.enroll(book, library.getId());
        assertThat(enrollBook.getId(), is(book.getId()));
        assertThat(enrollBook.getTitle(), is(book.getTitle()));
        assertThat(enrollBook.getAuthor(), is(book.getAuthor()));
        assertThat(enrollBook.getLibrary(), is(book.getLibrary()));

    }

    @Test
    public void searchAllTest() {
        assertThat(bookService.searchAll(), is(bookList));
    }

    @Test
    public void deleteTest() {
        bookService.delete(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @Test
    public void editTest() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Book editBook = bookService.edit(book, library.getId());
        assertThat(editBook.getTitle(), is(book.getTitle()));
    }

    @Test
    void borrowedBookMemberTest(){
        BorrowedBook book1 = bookService.borrowBook(book.getTitle(), member.getId());
        verify(borrowedBookRepository, times(1)).save(any());
        verify(bookRepository, times(1)).deleteById(any());
        assertThat(book1.getBook().getTitle(), is(book.getTitle()));
        assertThat(book1.getUser().getId(), is(member.getId()));
    }

    @Test
    void borrowedBookAdminTest(){
        BorrowedBook book2 = bookService.borrowBook(book.getTitle(), admin.getId());
        verify(borrowedBookRepository, times(1)).save(any());
        verify(bookRepository, times(1)).deleteById(any());
        assertThat(book2.getBook().getTitle(),is(book.getTitle()));
        assertThat(book2.getUser().getId(), is(admin.getId()));
    }

    @Test
    void validateOverBorrowedFailTest(){
        Member testMember = new Member("newmem", "newmempass", "newmemname");
        when(borrowedBookRepository.findByUserId(testMember.getId())).thenReturn(borrowedBookList);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    bookService.borrowBook(book.getTitle(), testMember.getId());
                }).withMessageMatching("빌린 책의 권수가 5권이 넘습니다.");
    }

    @Test
    void validateIsBorrowedFailTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    bookService.borrowBook("책제목3", member.getId());
                }).withMessageMatching("빌릴 수 있는 책이 없습니다.");
    }





}
