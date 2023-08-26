package com.filmosaurus.javaLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.repository.HomeRepository;

import lombok.Data;

@Data
@Service
public class HomeService {
    
    @Autowired
    private final HomeRepository homeRepository;

    public Iterable<Movie> getMovies() {
        return homeRepository.findAll();
    }
}
