package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/workintech/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.save(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Movie existing = movieService.findById(id);
        existing.setName(updatedMovie.getName());
        existing.setDirectorName(updatedMovie.getDirectorName());
        existing.setRating(updatedMovie.getRating());
        existing.setReleaseDate(updatedMovie.getReleaseDate());
        return ResponseEntity.ok(movieService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
        return ResponseEntity.ok(movie);
    }
}
