package com.filmosaurus.javaLearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmosaurus.javaLearning.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    List<Movie>findByDirectorContainsIgnoreCase(String param);
    List<Movie>findByTitleContainsIgnoreCase(String param);

}
