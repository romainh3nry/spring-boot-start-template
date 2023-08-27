package com.filmosaurus.javaLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.filmosaurus.javaLearning.service.HomeService;


@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home (Model model) {
        model.addAttribute("movies", homeService.getMovies());
        return "home";
    }

    @GetMapping("/movies/{id}")
    public <Optional>String getMovie(@PathVariable Long id, Model model) {
        model.addAttribute("movie", homeService.getMovie(id));
        return "detail";
    }
}
