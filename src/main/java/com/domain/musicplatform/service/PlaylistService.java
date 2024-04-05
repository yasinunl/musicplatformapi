package com.domain.musicplatform.service;

import com.domain.musicplatform.entity.Playlist;
import com.domain.musicplatform.entity.Song;
import com.domain.musicplatform.entity.User;
import com.domain.musicplatform.exception.BadExceptionHandler;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
import com.domain.musicplatform.repo.PlaylistRepo;
import com.domain.musicplatform.repo.SongRepo;
import com.domain.musicplatform.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final UserRepo userRepo;
    private final SongRepo songRepo;
    public List<Playlist> getAll(){
        return playlistRepo.findAll();
    }
    public Playlist getByPlaylistId(int id){
        return playlistRepo.findById(id).orElseThrow();
    }
    public Playlist createPlaylist(Playlist playlist){
        User user = userRepo.findById(playlist.getUser().getId()).orElseThrow();
        playlist.setUser(user);
        return playlistRepo.save(playlist);
    }
    public String deletePlaylist(int id){
        Optional<Playlist> optional = playlistRepo.findById(id);
        if (optional.isEmpty()) throw new BadExceptionHandler("Playlist is not found");
        Playlist playlist = optional.get();
        playlist.setUser(null);
        playlist.setSong(null);
        playlistRepo.delete(playlist);
        throw new SuccessExceptionHandler("Playlist is deleted success");
    }
    public Playlist addSong(Playlist playlist){
        Playlist thePlaylist = playlistRepo.findById(playlist.getId()).orElseThrow();
        for(Song song : playlist.getSong()){
            Song theSong = songRepo.findById(song.getId()).orElseThrow();
            thePlaylist.addSong(theSong);
        }
        return playlistRepo.save(thePlaylist);
    }
    public Playlist deleteSong(Playlist playlist){
        Playlist thePlaylist = playlistRepo.findById(playlist.getId()).orElseThrow();
        for(Song song : playlist.getSong()){
            Song theSong = songRepo.findById(song.getId()).orElseThrow();
            thePlaylist.getSong().remove(theSong);
        }
        return playlistRepo.save(thePlaylist);
    }
}
