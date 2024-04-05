package com.domain.musicplatform.service;

import com.domain.musicplatform.dto.SongDTO;
import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.entity.Genre;
import com.domain.musicplatform.entity.Song;
import com.domain.musicplatform.exception.BadExceptionHandler;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
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
public class SongService {
    private final SongRepo songRepo;
    private final GenreRepo genreRepo;
    private final ArtistRepo artistRepo;

    public List<Song> getAll(){
        return songRepo.findAll();
    }

    public Song getById(int id){
        return songRepo.findById(id).orElseThrow();
    }

    public Song addSong(SongDTO songDTO){
        if(songDTO.getArtist() == null) throw new BadExceptionHandler("Please indicate the artist");
        Song song = new Song();
        song.setSongLink(songDTO.getSongLink());
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        if(songDTO.getGenre() != null)
            for(Integer genreId : songDTO.getGenre()){
            Optional<Genre> optional = genreRepo.findById(genreId);
            optional.ifPresent(song::addGenre);
        }
        if(songDTO.getArtist() != null)
            for(Integer artistId : songDTO.getArtist()){
            Optional<Artist> optional =  artistRepo.findById(artistId);
            optional.ifPresent(song::addArtist);
        }
        return songRepo.save(song);
    }
    public Song updateSong(SongDTO theSong){
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getDuration() != 0) song.setDuration(theSong.getDuration());
        if(theSong.getSongLink() != null) song.setSongLink(theSong.getSongLink());
        if(theSong.getTitle() != null) song.setTitle(theSong.getTitle());
        return songRepo.save(song);
    }
    public Song addArtistToSong(SongDTO theSong){
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getArtist() != null){
            for(Integer artistId : theSong.getArtist())
                song.getArtist().add(artistRepo.findById(artistId).orElseThrow());
        }else return song;
        return songRepo.save(song);
    }
    public Song updateArtistForSong(SongDTO theSong){
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getArtist() != null){
            song.setArtist(new ArrayList<Artist>());
            for(Integer artistId : theSong.getArtist())
                song.getArtist().add(artistRepo.findById(artistId).orElseThrow());
        }else return song;
        return songRepo.save(song);
    }
    public Song deleteArtistFromSong(SongDTO theSong){
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getArtist() != null){
            for(Integer artistId : theSong.getArtist()){
                Artist artist = artistRepo.findById(artistId).orElseThrow();
                artist.getSong().remove(song);
                song.getArtist().remove(artist);
                artistRepo.save(artist);
            }
        }else return song;
        return songRepo.save(song);
    }
    public Song addGenreToSong(SongDTO theSong){
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getGenre() != null) {
            for (Integer genreId : theSong.getGenre())
                song.getGenre().add(genreRepo.findById(genreId).orElseThrow());
        } else return song;
        return songRepo.save(song);
    }
    public Song updateGenreForSong(SongDTO theSong){
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getGenre() != null) {
            song.setGenre(new ArrayList<Genre>());
            for (Integer genreId : theSong.getGenre())
                song.getGenre().add(genreRepo.findById(genreId).orElseThrow());
        } else return song;
        return songRepo.save(song);
    }
    public Song deleteGenreFromSong(SongDTO theSong){
        System.out.println("song");
        Song song = songRepo.findById(theSong.getId()).orElseThrow();
        if(theSong.getGenre() != null) {
            for (Integer genreId : theSong.getGenre())
                song.getGenre().remove(genreRepo.findById(genreId).orElseThrow());
        } else return song;
        return songRepo.save(song);
    }
    public String deleteSong(int id){
        Optional<Song> optional = songRepo.findById(id);
        Song song;
        if(optional.isPresent()) song = optional.get();
        else throw new BadExceptionHandler("Song is not found");
        songRepo.delete(song);
        throw new SuccessExceptionHandler("Song is deleted successfully");
    }
}
