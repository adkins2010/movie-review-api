package com.example.controller;

import com.example.repository.MovieReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    private static final String postBody = "{\n" +
            "  \"title\": \"Gremlins\",\n" +
            "  \"year\": 1984,\n" +
            "  \"reviewer\": \"Hercules Mulligan\",\n" +
            "  \"comment\": \"I loved it!\",\n" +
            "  \"starRating\": 3.0\n" +
            "}";

    private static final String postBodyNotFound = "{\n" +
            "  \"title\": \"Movie123\",\n" +
            "  \"year\": 1980,\n" +
            "  \"reviewer\": \"Awesome person\",\n" +
            "  \"comment\": \"Was terribad\",\n" +
            "  \"starRating\": 0.0\n" +
            "}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieReviewRepository movieReviewRepository;

    @Test
    @Rollback
    @Transactional
    public void testPostReview() throws Exception {
        MockHttpServletRequestBuilder request = post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody);

        this.mockMvc.perform(request).andExpect(status().isCreated())
                .andExpect(jsonPath("$.movie.title", equalTo("Gremlins")));
    }

    @Test
    @Rollback
    @Transactional
    public void testPostReviewWithMovieThatDoesNotExistShouldCreateMovie() throws Exception {
        MockHttpServletRequestBuilder request = post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBodyNotFound);

        this.mockMvc.perform(request).andExpect(status().isCreated())
                .andExpect(jsonPath("$.movie.title", equalTo("Movie123")));
    }

}
