package com.filmosaurus.javaLearning.model;

import lombok.Data;

@Data
public class AuthRequest {
    
    private String username;
    private String password;
}
