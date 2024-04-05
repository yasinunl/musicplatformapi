package com.domain.musicplatform.controller;

import com.domain.musicplatform.entity.Album;
import com.domain.musicplatform.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAll(){
        return ResponseEntity.ok(albumService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Album> getById(@PathVariable int id){
        return ResponseEntity.ok(albumService.getById(id));
    }
    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album){
        return ResponseEntity.ok(albumService.addAlbum(album));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable int id){
        return ResponseEntity.ok(albumService.deleteAlbum(id));
    }
    @PostMapping("/add-song")
    public ResponseEntity<Album> addSong(@RequestBody Album album){
        return ResponseEntity.ok(albumService.addSong(album));
    }
    @DeleteMapping("/delete-song")
    public ResponseEntity<Album> deleteSong(@RequestBody Album album){
        return ResponseEntity.ok(albumService.deleteSong(album));
    }
}
