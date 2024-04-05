package com.domain.musicplatform.repo;

import com.domain.musicplatform.entity.ArtistBiography;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistBiographyRepo extends JpaRepository<ArtistBiography, Integer> {
}
