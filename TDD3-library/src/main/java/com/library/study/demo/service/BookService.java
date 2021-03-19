package com.library.study.demo.service;

import com.library.study.demo.domain.*;
import com.library.study.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {



    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowedBookRepository borrowedBookRepository;

    public BookService(LibraryRepository libraryRepository, BookRepository bookRepository, UserRepository userRepository, BorrowedBookRepository borrowedBookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    public Book enroll(Book book, Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new IllegalArgumentException("없는 도서관 아이디입니다."));
        return bookRepository.save(new Book(book.getTitle(), book.getAuthor(), library));
    }

    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> searchAll() {
        return bookRepository.findAll();
    }

    public Book edit(Book book, Long libraryId) {
        Book editBook = findOne(book.getId());
        editBook.setTitle(book.getTitle());
        editBook.setAuthor(book.getAuthor());
        if (editBook.getLibrary().getId() != libraryId) {
            Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new IllegalArgumentException("없는 도서관 아이디입니다."));
            editBook.setLibrary(library);
        }
        return editBook;
    }

    public Book findOne(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 책 아이디입니다."));
    }

    public Book findByTitle(String title){
        return bookRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("해당하는 책 제목이 없습니다."));
    }


    public BorrowedBook borrowBook(String bookTitle, String userId) {
        List<Book> findBookList = bookRepository.findAllByTitleAndIsBorrowed(bookTitle, false);
        if (findBookList.size() == 0) {
            throw new IllegalArgumentException("빌릴 수 있는 책이 없습니다.");
        }
        if (findBorrowedBooks(userId).size() >= 5) {
            throw new IllegalArgumentException("빌린 책의 권수가 5권이 넘습니다.");
        }

        Book findBook = findBookList.get(0);
        User findUser = userRepository.findById(userId).orElse(null);
        BorrowedBook borrowedBook = new BorrowedBook(findBook, findUser, new Date());
        borrowedBookRepository.save(borrowedBook);
        findBook.setBorrowed(true);

        return borrowedBook;
    }

    public List<BorrowedBook> findBorrowedBooks(String userId) {
        return borrowedBookRepository.findByUserId(userId);
    }


}
