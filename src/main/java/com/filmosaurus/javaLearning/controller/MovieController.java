package com.filmosaurus.javaLearning.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.service.MovieService;

@RestController
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping("/api/movies")
    public Iterable<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/api/movies/create/movie")
    public Movie createMovie(@RequestBody Movie newMovie) {
        return movieService.createMovie(newMovie);
    }

    @GetMapping("/api/movies/find/{id}")
    public Optional<Movie> searchById(@PathVariable("id") Long id) {
        return movieService.searchMovieById(id);
    }

    @DeleteMapping("/api/movies/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        Optional<Movie> movie = movieService.searchMovieById(id);
        movieService.delete(movie.get());
        return "OK";
    }

    @GetMapping("/api/movies/search")
    public Iterable<Movie> searchMovies(@RequestParam String param, @RequestParam String search) {
        if (param.equals("director")) {
            return movieService.searchMoviesByDirector(search);
        }
        else if (param.equals("title")) {
            return movieService.SearchMoviesByTitle(search);
        }
        else {
            return Collections.emptyList();
        }
    }

    @PutMapping("/api/movies/update/{id}")
    public Movie update(@RequestBody Movie newMovie, @PathVariable("id") Long id) {
        Optional<Movie> movie = movieService.searchMovieById(id);
        movie.get().setTitle(newMovie.getTitle());
        movie.get().setDirector(newMovie.getDirector());
        movie.get().setPlot(newMovie.getPlot());
        movie.get().setRelease_date(newMovie.getRelease_date());
        return movieService.createMovie(movie.get());
    }
}
