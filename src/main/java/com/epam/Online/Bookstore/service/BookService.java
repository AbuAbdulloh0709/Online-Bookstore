package com.epam.Online.Bookstore.service;

import com.epam.Online.Bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    List<Book> searchBooksByTitle(String title);

    List<Book> searchBooksByAuthor(String authorName);

    List<Book> searchBooksByGenre(String genreName);

    Book saveBook(Book book);

    void deleteBook(Long id);
}
