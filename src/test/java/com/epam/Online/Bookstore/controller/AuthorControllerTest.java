package com.epam.Online.Bookstore.controller;

import com.epam.Online.Bookstore.model.Author;
import com.epam.Online.Bookstore.service.AuthorService;
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
import static org.mockito.Mockito.when;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors() {
        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(new Author(), new Author()));

        List<Author> authors = authorController.getAllAuthors();

        assertEquals(2, authors.size());
    }

    @Test
    void getAuthorById() {
        long authorId = 1L;
        when(authorService.getAuthorById(authorId)).thenReturn(Optional.of(new Author()));

        ResponseEntity<Author> responseEntity = authorController.getAuthorById(authorId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    void searchAuthorsByName() {
        String name = "John";
        when(authorService.searchAuthorsByName(name)).thenReturn(Arrays.asList(new Author(), new Author()));

        List<Author> authors = authorController.searchAuthorsByName(name);

        assertEquals(2, authors.size());
    }


    @Test
    void updateAuthor() {
        long authorId = 1L;
        Author existingAuthor = new Author();
        existingAuthor.setId(authorId);

        when(authorService.getAuthorById(authorId)).thenReturn(Optional.of(existingAuthor));
        when(authorService.saveAuthor(existingAuthor)).thenReturn(existingAuthor);

        ResponseEntity<Author> responseEntity = authorController.updateAuthor(authorId, existingAuthor);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
        assertEquals(authorId, responseEntity.getBody().getId());
    }
}