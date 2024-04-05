package com.domain.musicplatform.service;

import com.domain.musicplatform.entity.Genre;
import com.domain.musicplatform.exception.SuccessExceptionHandler;
import com.domain.musicplatform.repo.GenreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepo genreRepo;

    public List<Genre> getAll(){
        return genreRepo.findAll();
    }
    public Genre getById(int id){
        return genreRepo.findById(id).orElseThrow();
    }
    public Genre addGenre(Genre genre){
        return genreRepo.save(genre);
    }
    public String deleteGenre(int id){
        Genre genre = genreRepo.findById(id).orElseThrow();
        genre.setSong(null);
        genreRepo.delete(genre);
        throw new SuccessExceptionHandler("Genre is deleted successfully");
    }
    public Genre updateGenre(Genre genre){
        return genreRepo.save(genre);

    }
}
