package com.domain.musicplatform.service;

import com.domain.musicplatform.entity.Album;
import com.domain.musicplatform.entity.Artist;
import com.domain.musicplatform.entity.ArtistBiography;
import com.domain.musicplatform.entity.User;
import com.domain.musicplatform.exception.BadExceptionHandler;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
import com.domain.musicplatform.repo.AlbumRepo;
import com.domain.musicplatform.repo.ArtistBiographyRepo;
import com.domain.musicplatform.repo.ArtistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepo artistRepo;
    private final ArtistBiographyRepo artistBiographyRepo;
    private final AlbumRepo albumRepo;

    public List<Artist> getAllArtist(){
        return artistRepo.findAll();
    }
    public Artist getById(int id){
        Artist artist = artistRepo.findById(id).orElseThrow();
        artist.getArtistBiography().setLastUpdatedDate(new Date());
        return artistRepo.save(artist);
    }
    public Artist addArtist(Artist artist){
        Artist newArtist = artistRepo.save(artist);
        ArtistBiography newArtistBiography = new ArtistBiography();
        newArtistBiography.setId(newArtist.getId());
        newArtistBiography.setLastUpdatedDate(new Date());
        artist.setArtistBiography(artistBiographyRepo.save(newArtistBiography));
        return artist;
    }

    public String deleteArtist(int id){
        Optional<Artist> optional = artistRepo.findById(id);
        Artist artist;
        if(optional.isPresent()) artist = optional.get();
        else throw new BadExceptionHandler("Artist is not found");
        artist.setAccount(null);
        artist.setArtistBiography(null);
        artistRepo.delete(artist);
        throw new SuccessExceptionHandler("Artist deleted successfully");
    }

    public Artist updateArtist(Artist newArtist){
        if(newArtist.getId() == 0) throw new BadExceptionHandler("Please join an id");
        Optional<Artist> optional = artistRepo.findById(newArtist.getId());
        Artist artist;
        if(optional.isPresent()) artist = optional.get();
        else throw new BadExceptionHandler("Artist is not found");
        if(newArtist.getArtistName() != null) artist.setArtistName(newArtist.getArtistName());
        if(newArtist.getArtistBiography() != null) {
            if(newArtist.getArtistBiography().getBiographyText() != null)
                artist.getArtistBiography().setBiographyText(
                        newArtist.getArtistBiography().getBiographyText()
                );
        }
        artist.getArtistBiography().setLastUpdatedDate(new Date());
        return artistRepo.save(artist);
    }
}
