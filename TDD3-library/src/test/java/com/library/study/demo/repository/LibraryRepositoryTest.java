package com.library.study.demo.repository;

import com.library.study.demo.domain.Library;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;
    private Library library;

    @BeforeAll
    void setUp(){
        library = new Library();
    }

    @Test
    void saveLibraryTest(){
        Library saveLibrary = libraryRepository.save(library);
        assertThat(saveLibrary.getId(), is(1L));
        List<Library> libraryList = libraryRepository.findAll();
        assertThat(libraryList.size(), is(1));
    }

}
