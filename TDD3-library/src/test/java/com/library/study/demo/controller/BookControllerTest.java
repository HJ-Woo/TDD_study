package com.library.study.demo.controller;

import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.Library;
import com.library.study.demo.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate testTemplate;
    private HttpHeaders headers;
    private HttpHeaders failHeaders;
    private HttpEntity<BookDTO> entity;

    private BookDTO bookDTO;
    private Long bookId;
    private String resgisterUri = "/book/new";
    private String deleteUri = "/book/delete";
    private String findUri = "/book/find/";
    private String updateUri = "/book/update";
    private String borrowUri = "/book/borrow";
    private String borrwListUri = "/book/borrows";


    @BeforeAll
    void setUp(){
        bookDTO = new BookDTO("title", "author", 1L);
        bookId = 1L;
        Book book = new Book();
        book.setAuthor("au");
        book.setTitle("ti");

        headers = new HttpHeaders();
        headers.set("isAdmin", "true");
        failHeaders = new HttpHeaders();
        failHeaders.set("isAdmin", "false");

        entity = new HttpEntity<>(bookDTO, headers);
    }


    @Test
    void register_And_Find_Test(){
        ResponseEntity<String> responseEntity = testTemplate.postForEntity(resgisterUri, entity, String.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(containsString(BookController.registerMessage)));

        findUri += bookDTO.getTitle();
        ResponseEntity<BookDTO> responseDTO = testTemplate.exchange(findUri, HttpMethod.GET, new HttpEntity<>(headers), BookDTO.class);
        assertThat(responseDTO.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseDTO.getBody().getAuthor(), is(bookDTO.getAuthor()));
        assertThat(responseDTO.getBody().getLibraryId(), is(bookDTO.getLibraryId()));
        assertThat(responseDTO.getBody().getTitle(), is(bookDTO.getTitle()));
    }

    @Test
    void register_AND_deleteTest(){
        testTemplate.postForEntity(resgisterUri, bookDTO, String.class);
        ResponseEntity<String> responseEntity = testTemplate.postForEntity(deleteUri, new HttpEntity<Long>(bookId, headers), String.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(containsString(BookController.deleteMessage)));
    }

    @Test
    void register_And_UpdateTest(){
        testTemplate.postForEntity(resgisterUri, bookDTO, String.class);
        BookDTO updateDTO = new BookDTO(1L, "changeTitle", "chagneAuthor", 2L);
        ResponseEntity<String> responseEntity = testTemplate.postForEntity(updateUri, updateDTO, String.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(containsString(BookController.updateMessage)));
    }

    @Test
    void validateAdminFailTest(){
        HttpEntity<BookDTO> bookentity = new HttpEntity<>(bookDTO, failHeaders);

        ResponseEntity<String> responseEntity = testTemplate.postForEntity(resgisterUri, bookentity, String.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        assertThat(responseEntity.getBody(), is(ErrorMessage.notAdminMessage));
    }

    @Test
    void borrowTest(){
        BorrowDTO borrowDTO = new BorrowDTO("id1", "책제목1");
        ResponseEntity<BorrowedBookDTO> responseEntity = testTemplate.postForEntity(borrowUri, borrowDTO, BorrowedBookDTO.class);

        assertThat(responseEntity.getBody().getUserId(), is(borrowDTO.getUserId()));
        assertThat(responseEntity.getBody().getTitle(), is(borrowDTO.getBookTitle()));
    }

    @Test
    void showBorrowedListTest(){
        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.set("userId", "id1");
        ResponseEntity<List> responseEntity = testTemplate.exchange(borrwListUri, HttpMethod.GET, new HttpEntity<>(userHeaders), List.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().size(), is(3));
    }
}
