package com.epam.Online.Bookstore.service.impl;

import com.epam.Online.Bookstore.model.Book;
import com.epam.Online.Bookstore.repository.BookRepository;
import com.epam.Online.Bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> searchBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Book> searchBooksByGenre(String genreName) {
        return bookRepository.findByGenreNameContainingIgnoreCase(genreName);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

