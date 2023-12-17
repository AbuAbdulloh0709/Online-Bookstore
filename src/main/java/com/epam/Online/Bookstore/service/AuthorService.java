package com.epam.Online.Bookstore.service;

import com.epam.Online.Bookstore.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    List<Author> searchAuthorsByName(String name);

    Author saveAuthor(Author author);

    void deleteAuthor(Long id);
}

