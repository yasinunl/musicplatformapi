package com.domain.musicplatform.controller;

import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    ResponseEntity<List<Artist>> getAll(){
        return ResponseEntity.ok(artistService.getAllArtist());
    }

    @GetMapping("/{id}")
    ResponseEntity<Artist> getById(@PathVariable int id){
        return ResponseEntity.ok(artistService.getById(id));
    }

    @PostMapping
    ResponseEntity<Artist> addArtist(@RequestBody Artist artist){
        return ResponseEntity.ok(artistService.addArtist(artist));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteArtist(@PathVariable int id){
        return ResponseEntity.ok(artistService.deleteArtist(id));
    }

    @PutMapping
    ResponseEntity<Artist> updateArtist(@RequestBody Artist artist){
        return  ResponseEntity.ok(artistService.updateArtist(artist));
    }
}
