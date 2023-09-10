package com.filmosaurus.javaLearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.filmosaurus.javaLearning.configuration.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class JavaLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLearningApplication.class, args);
	}

}
