package com.filmosaurus.javaLearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmosaurus.javaLearning.model.Movie;

@Repository
public interface HomeRepository extends JpaRepository<Movie, Long> {
    
}
