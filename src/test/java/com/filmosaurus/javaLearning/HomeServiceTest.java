package com.filmosaurus.javaLearning;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.repository.HomeRepository;
import com.filmosaurus.javaLearning.service.HomeService;

@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    HomeService service;

    @Mock HomeRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new HomeService(repository);
    }

    @Test
    void getMoviesShouldReturnAll() {
        Movie movie1 = new Movie(
            "movie1_test_title",
            "movie1_test_director",
            "movie1_test_release_date",
            "movie1_test_plot"
        );
        Movie movie2 = new Movie(
            "movie2_test_title",
            "movie2_test_director",
            "movie3_test_release_date",
            "movie4_test_plot"
        );

        when(repository.findAll()).thenReturn(List.of(movie1, movie2));

        Iterable<Movie> movies = service.getMovies();

        assertThat(movies).containsExactly(movie1, movie2);
    }

    @Test
    void creatingANewMovieShouldReturnTheSameData() {
        given(repository.save(any(Movie.class)))
        .willReturn(
            new Movie(
                "test_title",
                "test_director",
                "test_release_date",
                "test_plot"
            )
        );

        Movie new_movie = service.create(
            new Movie(
                "test_title",
                "test_director",
                "test_release_date",
                "test_plot"
            )
        );

        assertThat(new_movie.getTitle()).isEqualTo("test_title");
        assertThat(new_movie.getDirector()).isEqualTo("test_director");
        assertThat(new_movie.getRelease_date()).isEqualTo("test_release_date");
        assertThat(new_movie.getPlot()).isEqualTo("test_plot");
    }
}
