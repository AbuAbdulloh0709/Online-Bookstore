package com.epam.Online.Bookstore.controller;

import com.epam.Online.Bookstore.model.Genre;
import com.epam.Online.Bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/name")
    public List<Genre> searchGenresByName(@RequestParam String name) {
        return genreService.searchGenresByName(name);
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        if (!genreService.getGenreById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        genre.setId(id);
        return ResponseEntity.ok(genreService.saveGenre(genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        if (!genreService.getGenreById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}

