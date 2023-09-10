package com.filmosaurus.javaLearning;

import static com.filmosaurus.javaLearning.HomeRepositoryTestcontainersTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.testcontainers.junit.jupiter.Container;

import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.repository.HomeRepository;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = DataSourceInitializer.class)
public class HomeRepositoryTestcontainersTest {

    @Autowired HomeRepository repository;

    @Container
    static final PostgreSQLContainer<?> database = 
        new PostgreSQLContainer<>("postgres:9.6.12").withUsername("postgres");

    static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                applicationContext,
                "spring.datasource.url=" + database.getJdbcUrl(),
                "spring.database.username=" + database.getUsername(),
                "spring.datasource.password=" + database.getPassword(),
                "spring.jpa.hibernate.dll-auto=create-drop"
            );
        }
    }

    @BeforeEach
    void setUp() {
        repository.saveAll(
            List.of(
                new Movie("test_field_1", "test_field_1", "test_field_1", "test_field_1"),     
                new Movie("test_field_2", "test_field_2", "test_field_2", "test_field_2"),      
                new Movie("test_field_3", "test_field_3", "test_field_3", "test_field_3")      
            )
        );
    }

    @Test
    void findAllShouldProduceAllMovies() {
        List<Movie> movies = repository.findAll();
        assertThat(movies).hasSize(3);
    }
}
