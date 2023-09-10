package com.filmosaurus.javaLearning.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.filmosaurus.javaLearning.model.User;

@ConfigurationProperties("app.config")
public record AppConfig(String header, String intro, List<User> users) {
    
}
