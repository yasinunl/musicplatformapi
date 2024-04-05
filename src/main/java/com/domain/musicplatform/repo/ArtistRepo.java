package com.domain.musicplatform.repo;

import com.domain.musicplatform.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Integer> {
    List<Artist> findByArtistName(String name);
    Optional<Artist> findArtistByArtistName(String name);
}
