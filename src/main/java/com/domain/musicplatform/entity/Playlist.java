package com.domain.musicplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String playlistTitle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("playlist")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    @JsonIgnoreProperties("playlist")
    private List<Song> song;

    public void addSong(Song song){
        if(this.song == null) this.song = new ArrayList<Song>();
        this.song.add(song);
    }
}
