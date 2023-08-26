package com.filmosaurus.javaLearning.controller;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Optional;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.service.MovieService;

@RestController
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public Iterable<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/create/movie")
    public Movie createMovie(@RequestBody Movie newMovie) {
        return movieService.createMovie(newMovie);
    }

    @GetMapping("movies/{id}")
    public Optional<Movie> searchById(@PathVariable("id") Long id) {
        return movieService.searchMovieById(id);
    }

    @DeleteMapping("movies/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        Optional<Movie> movie = movieService.searchMovieById(id);
        movieService.delete(movie.get());
        return "OK";
    }

    @GetMapping("movies/search")
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
}
