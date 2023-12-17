package com.epam.Online.Bookstore.service.impl;

import com.epam.Online.Bookstore.model.Book;
import com.epam.Online.Bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    void getBookById() {
        long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));

        Optional<Book> book = bookService.getBookById(bookId);

        assertTrue(book.isPresent());
    }

    @Test
    void searchBooksByTitle() {
        String title = "Spring";
        when(bookRepository.findByTitleContainingIgnoreCase(title)).thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookService.searchBooksByTitle(title);

        assertEquals(2, books.size());
    }

    @Test
    void searchBooksByAuthor() {
        String authorName = "John Doe";
        when(bookRepository.findByAuthorNameContainingIgnoreCase(authorName))
                .thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookService.searchBooksByAuthor(authorName);

        assertEquals(2, books.size());
    }

    @Test
    void searchBooksByGenre() {
        String genreName = "Science Fiction";
        when(bookRepository.findByGenreNameContainingIgnoreCase(genreName))
                .thenReturn(Arrays.asList(new Book(), new Book()));

        List<Book> books = bookService.searchBooksByGenre(genreName);

        assertEquals(2, books.size());
    }

    @Test
    void saveBook() {
        Book bookToSave = new Book();
        when(bookRepository.save(bookToSave)).thenReturn(bookToSave);

        Book savedBook = bookService.saveBook(bookToSave);

        assertEquals(bookToSave, savedBook);
    }

    @Test
    void deleteBook() {
        long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
