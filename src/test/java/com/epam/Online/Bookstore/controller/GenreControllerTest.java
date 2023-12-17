package com.epam.Online.Bookstore.controller;

import com.epam.Online.Bookstore.model.Genre;
import com.epam.Online.Bookstore.service.GenreService;
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

class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllGenres() {
        when(genreService.getAllGenres()).thenReturn(Arrays.asList(new Genre(), new Genre()));

        List<Genre> genres = genreController.getAllGenres();

        assertEquals(2, genres.size());
    }

    @Test
    void getGenreById() {
        long genreId = 1L;
        when(genreService.getGenreById(genreId)).thenReturn(Optional.of(new Genre()));

        ResponseEntity<Genre> responseEntity = genreController.getGenreById(genreId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
    }

    @Test
    void searchGenresByName() {
        String name = "Fiction";
        when(genreService.searchGenresByName(name)).thenReturn(Arrays.asList(new Genre(), new Genre()));

        List<Genre> genres = genreController.searchGenresByName(name);

        assertEquals(2, genres.size());
    }
    @Test
    void updateGenre() {
        long genreId = 1L;
        Genre existingGenre = new Genre();
        existingGenre.setId(genreId);

        when(genreService.getGenreById(genreId)).thenReturn(Optional.of(existingGenre));
        when(genreService.saveGenre(existingGenre)).thenReturn(existingGenre);

        ResponseEntity<Genre> responseEntity = genreController.updateGenre(genreId, existingGenre);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() != null);
        assertEquals(genreId, responseEntity.getBody().getId());
    }
}

