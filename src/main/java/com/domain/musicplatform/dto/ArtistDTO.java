package com.domain.musicplatform.dto;

import com.domain.musicplatform.entity.ArtistBiography;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "artist")
public class ArtistDTO {
    @Id
    private int id;
    private String artistName;

    @PrimaryKeyJoinColumn
    @OneToOne
    private ArtistBiography artistBiography;

}
