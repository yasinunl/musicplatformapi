package com.domain.musicplatform.dto;

import com.domain.musicplatform.entity.Album;
import com.domain.musicplatform.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
public class SongDTO {
    private int id;
    private String title;
    private String songLink;
    private int duration;
    private List<Integer> genre;
    private List<Integer> artist;
}
