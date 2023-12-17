package com.epam.Online.Bookstore.controller;

import com.epam.Online.Bookstore.model.Book;
import com.epam.Online.Bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllBooks() {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookController.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    void getBookById() {
        long bookId = 1L;
        when(bookService.getBookById(bookId)).thenReturn(Optional.of(new Book()));

        ResponseEntity<Book> responseEntity = bookController.getBookById(bookId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    void searchBooksByTitle() {
        String title = "Spring";
        when(bookService.searchBooksByTitle(title)).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookController.searchBooksByTitle(title);

        assertEquals(2, books.size());
    }


    @Test
    void searchBooksByAuthor() {
        String authorName = "John Doe";
        when(bookService.searchBooksByAuthor(authorName)).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookController.searchBooksByAuthor(authorName);

        assertEquals(2, books.size());
    }

    @Test
    void searchBooksByGenre() {
        String genreName = "Science Fiction";
        when(bookService.searchBooksByGenre(genreName)).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookController.searchBooksByGenre(genreName);

        assertEquals(2, books.size());
    }

    @Test
    void createBook() {
        Book bookToCreate = new Book();
        when(bookService.saveBook(bookToCreate)).thenReturn(bookToCreate);

        Book createdBook = bookController.createBook(bookToCreate);

        assertEquals(bookToCreate, createdBook);
    }

    @Test
    void updateBook() {
        long bookId = 1L;
        Book existingBook = new Book();
        existingBook.setId(bookId);

        when(bookService.getBookById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookService.saveBook(existingBook)).thenReturn(existingBook);

        ResponseEntity<Book> responseEntity = bookController.updateBook(bookId, existingBook);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
        assertEquals(bookId, responseEntity.getBody().getId());
    }

    @Test
    void deleteBook() {
        long bookId = 1L;
        when(bookService.getBookById(bookId)).thenReturn(Optional.of(new Book()));

        ResponseEntity<Void> responseEntity = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() == null);
        verify(bookService, times(1)).deleteBook(bookId);
    }}