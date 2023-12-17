package com.epam.Online.Bookstore.service.impl;

import com.epam.Online.Bookstore.model.Author;
import com.epam.Online.Bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(new Author(), new Author()));

        List<Author> authors = authorService.getAllAuthors();

        assertEquals(2, authors.size());
    }

    @Test
    void getAuthorById() {
        long authorId = 1L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(new Author()));

        Optional<Author> author = authorService.getAuthorById(authorId);

        assertTrue(author.isPresent());
    }

    @Test
    void searchAuthorsByName() {
        String name = "John";
        when(authorRepository.findByNameContainingIgnoreCase(name)).thenReturn(Arrays.asList(new Author(), new Author()));

        List<Author> authors = authorService.searchAuthorsByName(name);

        assertEquals(2, authors.size());
    }

    @Test
    void saveAuthor() {
        Author authorToSave = new Author();
        when(authorRepository.save(authorToSave)).thenReturn(authorToSave);

        Author savedAuthor = authorService.saveAuthor(authorToSave);

        assertEquals(authorToSave, savedAuthor);
    }

    @Test
    void deleteAuthor() {
        long authorId = 1L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(new Author()));

        authorService.deleteAuthor(authorId);

        verify(authorRepository, times(1)).deleteById(authorId);
    }
}

