package com.domain.musicplatform.controller;

import com.domain.musicplatform.entity.Genre;
import com.domain.musicplatform.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable int id) {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.addGenre(genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable int id) {
        return ResponseEntity.ok(genreService.deleteGenre(id));
    }

    @PutMapping("")
    public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.updateGenre(genre));
    }
}
