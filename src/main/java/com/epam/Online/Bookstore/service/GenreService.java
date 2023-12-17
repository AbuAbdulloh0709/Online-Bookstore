package com.epam.Online.Bookstore.service;

import com.epam.Online.Bookstore.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(Long id);

    List<Genre> searchGenresByName(String name);

    Genre saveGenre(Genre genre);

    void deleteGenre(Long id);
}


