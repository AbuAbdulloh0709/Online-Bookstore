package com.epam.Online.Bookstore.service.impl;

import com.epam.Online.Bookstore.model.Genre;
import com.epam.Online.Bookstore.repository.GenreRepository;
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

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllGenres() {
        when(genreRepository.findAll()).thenReturn(Arrays.asList(new Genre(), new Genre()));

        List<Genre> genres = genreService.getAllGenres();

        assertEquals(2, genres.size());
    }

    @Test
    void getGenreById() {
        long genreId = 1L;
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(new Genre()));

        Optional<Genre> genre = genreService.getGenreById(genreId);

        assertTrue(genre.isPresent());
    }

    @Test
    void searchGenresByName() {
        String name = "Fiction";
        when(genreRepository.findByNameContainingIgnoreCase(name)).thenReturn(Arrays.asList(new Genre(), new Genre()));

        List<Genre> genres = genreService.searchGenresByName(name);

        assertEquals(2, genres.size());
    }

    @Test
    void updateGenre() {
        long genreId = 1L;
        Genre existingGenre = new Genre();
        existingGenre.setId(genreId);

        when(genreRepository.findById(genreId)).thenReturn(Optional.of(existingGenre));
        when(genreRepository.save(existingGenre)).thenReturn(existingGenre);

        Genre updatedGenre = genreService.saveGenre(existingGenre);

        assertEquals(genreId, updatedGenre.getId());
    }

}
