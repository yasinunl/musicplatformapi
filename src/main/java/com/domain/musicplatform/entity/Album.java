package com.domain.musicplatform.entity;

import com.domain.musicplatform.dto.ArtistDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String albumTitle;
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonIgnoreProperties({"album", "song"})
    private Artist artist;

    @OneToMany(mappedBy = "album")
    @JsonIgnoreProperties({"album", "artist"})
    private List<Song> song;

    public void addSong(Song theSong){
        if(song == null) song = new ArrayList<Song>();
        song.add(theSong);
    }


}
