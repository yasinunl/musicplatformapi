package com.domain.musicplatform.service;

import com.domain.musicplatform.dto.AccountDTO;
import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.entity.Song;
import com.domain.musicplatform.entity.User;
import com.domain.musicplatform.exception.BadExceptionHandler;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
import com.domain.musicplatform.repo.ArtistRepo;
import com.domain.musicplatform.repo.PlaylistRepo;
import com.domain.musicplatform.repo.SongRepo;
import com.domain.musicplatform.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;
    private final ArtistRepo artistRepo;
    private final SongRepo songRepo;
    private final PlaylistRepo playlistRepo;

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User getById(int id){
        User user = userRepo.findById(id).orElseThrow();
        user.setLastLoginDate(new Date());
        return userRepo.save(user);
    }

    public User addUser(User user){
        return userRepo.save(user);
    }
    public User addArtist(AccountDTO accountDTO){
        if(accountDTO.getUserId() == 0 ||
                (accountDTO.getArtistId() == 0 && accountDTO.getArtistName() == null))
            throw new BadExceptionHandler("User or account doesn't exist");
        Artist artist;
        User user = userRepo.findById(accountDTO.getUserId()).orElseThrow();
        if(accountDTO.getArtistId() != 0)
            artist = artistRepo.findById(accountDTO.getArtistId()).orElseThrow();
        else{
            List<Artist> isArtistExist = artistRepo.findByArtistName(accountDTO.getArtistName());
            if(!isArtistExist.isEmpty()) artist = isArtistExist.get(0);
            else{
                Artist newArtist = new Artist();
                newArtist.setArtistName(accountDTO.getArtistName());
                artist = artistRepo.save(newArtist);
            }
        }

        user.addArtist(artist);
        return userRepo.save(user);

    }

    public User deleteArtist(AccountDTO accountDTO){
        if(accountDTO.getUserId() == 0 ||
                (accountDTO.getArtistId() == 0 && accountDTO.getArtistName() == null))
            throw new BadExceptionHandler("User or account doesn't exist");
        Artist artist = artistRepo.findById(accountDTO.getArtistId()).orElseThrow();
        User user = userRepo.findById(accountDTO.getUserId()).orElseThrow();
        if(user.getAccount().isEmpty()) return user;
        user.getAccount().remove(artist);
        artist.getAccount().remove(user);
        if(artist.getAccount().isEmpty()) artistRepo.delete(artist);
        return userRepo.save(user);

    }

    public String deleteUser(int id){
        User user = userRepo.findById(id).orElseThrow();
        for(Artist account : user.getAccount()){
            account.getAccount().remove(user);
        }
        user.setAccount(null);
        user.setFavorites(null);
        playlistRepo.deleteAll(user.getPlaylist());
        user.setPlaylist(null);
        userRepo.delete(user);
        throw new SuccessExceptionHandler("User deleted successfully");
    }

    public User updateUser(User newUser){
        User user = userRepo.findById(newUser.getId()).orElseThrow();

        if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
        if(newUser.getUsername() != null) user.setUsername(newUser.getUsername());

        user.setLastLoginDate(new Date());
        return userRepo.save(user);
    }
    public User addFavoriteSong(User user){
        User theUser = userRepo.findById(user.getId()).orElseThrow();
        for(Song song : user.getFavorites()){
            Optional<Song> optional = songRepo.findById(song.getId());
            optional.ifPresent(theUser::addSong);
        }
        return userRepo.save(theUser);
    }
    public User deleteFavoriteSong(User user){
        User theUser = userRepo.findById(user.getId()).orElseThrow();
        for(Song song : user.getFavorites()){
            Optional<Song> optional = songRepo.findById(song.getId());
            optional.ifPresent(value -> theUser.getFavorites().remove(value));
        }
        return userRepo.save(theUser);
    }

    public User login(User user) {
        if(Objects.equals(user.getEmail(), "") & Objects.equals(user.getPassword(), ""))
            throw new BadExceptionHandler("Email and password can't be empty!");
        Optional<User> optional = userRepo.getUserByEmailAndPassword(user.getPassword(),user.getEmail());
        if(optional.isEmpty())
            throw new BadExceptionHandler("Email or password is wrong");
        return optional.get();
    }
    public User register(User user) {
        if(Objects.equals(user.getEmail(), "") || Objects.equals(user.getPassword(), "") || Objects.equals(user.getUsername(), ""))
            throw new BadExceptionHandler("Email and password can't be empty!");
        return userRepo.save(user);
    }
}
