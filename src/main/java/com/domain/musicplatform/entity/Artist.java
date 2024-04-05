package com.domain.musicplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String artistName;

    @PrimaryKeyJoinColumn
    @OneToOne(
            cascade = CascadeType.ALL, orphanRemoval = true)
    private ArtistBiography artistBiography;

    @ManyToMany
    @JoinTable(
            name = "account",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("account")
    private List<User> account;

    @ManyToMany
    @JoinTable(
            name = "perform_on_song",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    @JsonIgnoreProperties("song")
    private List<Song> song;

    @OneToMany(mappedBy = "artist")
    private List<Album> album;


    public void addSong(Song theSong){
        if(song == null) song = new ArrayList<Song>();
        song.add(theSong);
    }

    public void addUser(User user){
        if(account == null) account = new ArrayList<User>();
        account.add(user);
    }


}
