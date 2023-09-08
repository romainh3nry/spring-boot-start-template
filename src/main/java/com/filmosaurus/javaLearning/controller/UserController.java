package com.filmosaurus.javaLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmosaurus.javaLearning.model.AuthRequest;
import com.filmosaurus.javaLearning.model.User;
import com.filmosaurus.javaLearning.service.JwtService;
import com.filmosaurus.javaLearning.service.UserInfoDetails;
import com.filmosaurus.javaLearning.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
    
    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/user/info")
    public Object getAuthenticateUserInfo(@AuthenticationPrincipal UserInfoDetails userInfoDetails) {
        return userInfoDetails.getUserInfo();
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
