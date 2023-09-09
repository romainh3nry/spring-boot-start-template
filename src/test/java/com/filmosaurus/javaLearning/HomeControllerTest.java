package com.filmosaurus.javaLearning;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.filmosaurus.javaLearning.controller.HomeController;
import com.filmosaurus.javaLearning.model.Movie;
import com.filmosaurus.javaLearning.repository.HomeRepository;
import com.filmosaurus.javaLearning.service.HomeService;
import com.filmosaurus.javaLearning.service.JwtService;
import com.filmosaurus.javaLearning.service.UserService;

@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {
    
    @Autowired MockMvc mvc;

    @MockBean HomeService homeService;
    @MockBean JwtService jwtService;
    @MockBean UserService userService;
    @MockBean HomeRepository homeRepository;

    @Test
    @WithMockUser
    void indexPageHasHtmlForm() throws Exception {
        String html = mvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(
            content().string(
                containsString("Welcome")
            )
        )
        .andReturn()
        .getResponse().getContentAsString();

        assertThat(html).contains(
            "<form action=\"/movies/create\""
        );
    }

    @Test
    @WithMockUser
    void postNewMovieShouldWork() throws Exception {
        mvc.perform(
            post("/movies/create")
                .param("title", "new_title")
                .param("director", "new_director")
                .param("release_date", "new_release_date")
                .param("plot", "new_plot")
                .with(csrf())
        )
        .andExpect(redirectedUrl("/"));

        verify(homeService).create(
            new Movie(
                "new_title",
                "new_director",
                "new_release_date",
                "new_plot"
            )
        );
    }
}
