package com.epam.Online.Bookstore.repository;

import com.epam.Online.Bookstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByNameContainingIgnoreCase(String name);
}
