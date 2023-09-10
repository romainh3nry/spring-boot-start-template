package com.filmosaurus.javaLearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.testcontainers.junit.jupiter.Container;

import com.filmosaurus.javaLearning.repository.HomeRepository;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
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
}
