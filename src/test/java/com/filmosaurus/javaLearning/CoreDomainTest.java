package com.filmosaurus.javaLearning;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.filmosaurus.javaLearning.model.Movie;

public class CoreDomainTest {
    
    @Test
    void newMovieShouldHaveNullID() {
        Movie movie = new Movie(
            "test_title", "test_director", "test_release_date", "test_plot" 
        );

        assertThat(movie.getId()).isNull();
        assertThat(movie.getTitle()).isEqualTo("test_title");
        assertThat(movie.getDirector()).isEqualTo("test_director");
        assertThat(movie.getRelease_date()).isEqualTo("test_release_date");
        assertThat(movie.getPlot()).isEqualTo("test_plot");

        assertThat(movie.toString())
            .isEqualTo(
                "Movie{id=null, title='test_title', director='test_director', release_date='test_release_date', plot='test_plot'}"
            );
    }
}
