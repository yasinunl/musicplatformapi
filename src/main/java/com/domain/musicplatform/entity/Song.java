package com.domain.musicplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String songLink;
    private int duration;

    @ManyToMany
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties("genre")
    private List<Genre> genre;

    @ManyToMany
    @JoinTable(
            name = "perform_on_song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @JsonIgnoreProperties({"song","album"})
    private List<Artist> artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonIgnoreProperties("song")
    private Album album;

    public void addGenre(Genre newGenre){
        if(genre == null) genre = new ArrayList<>();
        genre.add(newGenre);
    }
    public void addArtist(Artist theArtist){
        if(artist == null) artist = new ArrayList<Artist>();
        artist.add(theArtist);
    }
}
