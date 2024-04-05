package com.domain.musicplatform.controller;
import com.domain.musicplatform.dto.SongDTO;
import com.domain.musicplatform.entity.Song;
import com.domain.musicplatform.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable int id) {
        Song song = songService.getById(id);
        return ResponseEntity.ok(song);
    }

    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody SongDTO songDTO) {
        Song createdSong = songService.addSong(songDTO);
        return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Song> updateSong(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.updateSong(songDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable int id){
        return ResponseEntity.ok(songService.deleteSong(id));
    }
    @PostMapping("/add-genre")
    public ResponseEntity<Song> addGenreToSong(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.addGenreToSong(songDTO));
    }
    @DeleteMapping("/delete-genre")
    public ResponseEntity<Song> deleteGenreFromSong(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.deleteGenreFromSong(songDTO));
    }
    @PostMapping("/add-artist")
    public ResponseEntity<Song> addGenreToArtist(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.addArtistToSong(songDTO));
    }
    @DeleteMapping("/delete-artist")
    public ResponseEntity<Song> deleteArtistFromSong(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.deleteArtistFromSong(songDTO));
    }
    @PutMapping("/update-artist")
    public ResponseEntity<Song> updateArtistForSong(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.updateArtistForSong(songDTO));
    }
    @PutMapping("/update-genre")
    public ResponseEntity<Song> updateGenreForSong(@RequestBody SongDTO songDTO){
        return ResponseEntity.ok(songService.updateGenreForSong(songDTO));
    }
}
