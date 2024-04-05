package com.domain.musicplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Date registrationDate = new Date();
    private Date lastLoginDate;

    @ManyToMany
    @JoinTable(
            name = "account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @JsonIgnoreProperties("account")
    private List<Artist> account;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Playlist> playlist;

    @ManyToMany
    @JoinTable(
            name = "user_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> favorites;
    public void addSong(Song song){
        if(favorites == null) favorites = new ArrayList<Song>();
        favorites.add(song);
    }

    public void addPlaylist(Playlist playlist){
        if(this.playlist == null) this.playlist = new ArrayList<Playlist>();
        this.playlist.add(playlist);
    }
    public void addArtist(Artist artist){
        if(account == null) account = new ArrayList<Artist>();
        account.add(artist);
    }
}
