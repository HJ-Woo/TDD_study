package com.library.study.demo.repository;

import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.Library;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    private Book book;

    @BeforeAll
    public void setUp() {
        Library library = new Library();
        Library saveLibrary = libraryRepository.save(library);

        book = new Book("ABC","asioo", saveLibrary);
    }

    public Book saveBook(){
        return bookRepository.save(book);
    }

    @Test
    public void bookSaveTest(){
        Book saveBook = bookRepository.save(book);
        assertThat(saveBook.getTitle(), is(book.getTitle()));
        assertThat(saveBook.getAuthor(), is(book.getAuthor()));
        List<Book> bookList = bookRepository.findAll();

        bookRepository.deleteById(saveBook.getId());
        List<Book> deleteBookList = bookRepository.findAll();
        assertThat(deleteBookList.size(), is(bookList.size() - 1));
         //Exception
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Book findBook = bookRepository.findById(saveBook.getId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 아이디입니다."));
                }).withMessageMatching("찾을 수 없는 아이디입니다.");
    }

    @Test
    void findBookTest(){
        Book saveBook = saveBook();
        Book findBook = bookRepository.findById(saveBook.getId()).orElse(null);
        assertThat(findBook.getId(), is(saveBook.getId()));
    }

    @Test
    void findByIdBookTest(){
        Book saveBook = saveBook();
        Book findBook = bookRepository.findByTitle(saveBook.getTitle()).orElse(null);
        assertThat(findBook.getId(), is(saveBook.getId()));
        assertThat(findBook.getAuthor(), is(saveBook.getAuthor()));
    }


    @Test
    void updateTest(){
        Book saveBook = saveBook();
        //dirty checking
        //지연쓰기란 ?? Transaction 이후에
        // DB에 저장해둔 값이랑, 지금 영속성이 부여되어있는 Book의 상태를 비교해서 다를경우에는

        String changeTitle = "책2";
        saveBook.setTitle(changeTitle);
        Book updateBook = bookRepository.findById(saveBook.getId()).orElse(null);

        assertThat(updateBook.getTitle(), is(changeTitle));
    }

    @Test
    void findAllByTitleANDIsBorrowed(){
        List<Book> books = bookRepository.findAllByTitleAndIsBorrowed("책제목4", false);
        assertThat(books.size(), is(2));
        books.forEach(book ->{
            assertThat(book, is(instanceOf(Book.class)));
            assertThat(book.getAuthor(), is("작가4"));
        });
    }


}
