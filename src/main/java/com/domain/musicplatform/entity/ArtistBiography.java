package com.domain.musicplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "artist_biography")
public class ArtistBiography {
    @Id
    private int id;
    private String biographyText;
    private Date lastUpdatedDate;
}
