package com.domain.musicplatform.controller;

import com.domain.musicplatform.entity.Playlist;
import com.domain.musicplatform.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;
    @GetMapping
    public ResponseEntity<List<Playlist>> getAll(){
        return ResponseEntity.ok(playlistService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getByPlaylistId(@PathVariable int id){
        return ResponseEntity.ok(playlistService.getByPlaylistId(id));
    }
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist){
        return ResponseEntity.ok(playlistService.createPlaylist(playlist));
    }
    @PostMapping("/add-song")
    public ResponseEntity<Playlist> addSong(@RequestBody Playlist playlist){
        return ResponseEntity.ok(playlistService.addSong(playlist));
    }
    @DeleteMapping("/delete-song")
    public ResponseEntity<Playlist> deleteSong(@RequestBody Playlist playlist){
        return ResponseEntity.ok(playlistService.deleteSong(playlist));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable int id){
        return ResponseEntity.ok(playlistService.deletePlaylist(id));
    }
}
