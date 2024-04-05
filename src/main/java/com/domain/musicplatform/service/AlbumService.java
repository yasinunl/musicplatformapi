package com.domain.musicplatform.service;

import com.domain.musicplatform.entity.Album;
import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.entity.Genre;
import com.domain.musicplatform.entity.Song;
import com.domain.musicplatform.exception.BadExceptionHandler;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
import com.domain.musicplatform.repo.AlbumRepo;
import com.domain.musicplatform.repo.ArtistRepo;
import com.domain.musicplatform.repo.GenreRepo;
import com.domain.musicplatform.repo.SongRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepo albumRepo;
    private final ArtistRepo artistRepo;
    private final SongRepo songRepo;
    private final GenreRepo genreRepo;

    public List<Album> getAll() {
        return albumRepo.findAll();
    }

    public Album getById(int id) {
        Optional<Album> optional = albumRepo.findById(id);
        if(optional.isEmpty()) throw new RuntimeException("The album is not found");
        return optional.get();
    }

    public Album addAlbum(Album album) {
        Optional<Artist> optional;
        if (album.getArtist().getId() != 0)
            optional = artistRepo.findById(album.getArtist().getId());
        else
            optional = artistRepo.findArtistByArtistName(album.getArtist().getArtistName());
        album.setArtist(null);
        optional.ifPresent(album::setArtist);
        album.setSong(null);
        return albumRepo.save(album);
    }
    public String deleteAlbum(int id){
        Optional<Album> optional = albumRepo.findById(id);
        Album album;
        if(optional.isPresent()) album = optional.get();
        else throw new BadExceptionHandler("The album is not found");
        for (Song song : album.getSong()){
            song.setAlbum(null);
        }
        album.getArtist().setAlbum(null);
        album.setSong(null);
        albumRepo.delete(album);
        throw new SuccessExceptionHandler("The album is deleted success");
    }
    public Album updateAlbum(Album album){
        Optional<Album> optional =albumRepo.findById(album.getId());
        if(optional.isEmpty()) throw new BadExceptionHandler("The album is not found");
        Album theAlbum = optional.get();
        if(album.getAlbumTitle() != null) theAlbum.setAlbumTitle(album.getAlbumTitle());
        if(album.getReleaseDate() != null) theAlbum.setReleaseDate(album.getReleaseDate());
        return albumRepo.save(theAlbum);
    }
    public Album addSong(Album album){
        Optional<Album> optionalA =albumRepo.findById(album.getId());
        if(optionalA.isEmpty()) throw new BadExceptionHandler("The album is not found");
        Album theAlbum = optionalA.get();
        for(Song song : album.getSong()){
            Optional<Song> optional = songRepo.findById(song.getId());
            if(optional.isPresent()){
                Song theSong = optional.get();
                theSong.setAlbum(theAlbum);
                theAlbum.addSong(theSong);
            }
        }
        return albumRepo.save(theAlbum);
    }
    public Album deleteSong(Album album){
        Optional<Album> optionalA =albumRepo.findById(album.getId());
        if(optionalA.isEmpty()) throw new BadExceptionHandler("The album is not found");
        Album theAlbum = optionalA.get();
        for(Song song : album.getSong()){
            Optional<Song> optional = songRepo.findById(song.getId());
            if(optional.isPresent()){
                Song theSong = optional.get();
                theSong.setAlbum(theAlbum);
                theAlbum.getSong().remove(theSong);
            }
        }
        return albumRepo.save(theAlbum);
    }
}
