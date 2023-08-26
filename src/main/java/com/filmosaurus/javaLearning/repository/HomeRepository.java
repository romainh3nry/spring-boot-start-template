package com.filmosaurus.javaLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmosaurus.javaLearning.model.Movie;

public interface HomeRepository extends JpaRepository<Movie, Long> {
    
}
