package com.filmosaurus.javaLearning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.repository.MovieRepository;

import lombok.Data;

@Data
@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> searchMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie delete(Movie movie) {
        movieRepository.delete(movie);
        return movie;
    }
}
