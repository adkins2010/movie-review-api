package com.allstate.movie.reviews.controller;

import com.allstate.movie.reviews.model.MovieReviewRequest;
import com.allstate.movie.reviews.model.MovieReviewResult;
import com.allstate.movie.reviews.repository.MovieReviewRepository;
import com.allstate.movie.reviews.services.MovieSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class MovieReviewsControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    MovieSearchService service;
    @Autowired
    MovieReviewRepository repository;

//    @BeforeClass
//    public static void setUpBeforeClass() {
//        ConsoleAppender console = new ConsoleAppender(); //create appender
//        //configure the appender
//        String PATTERN = "%d [%p|%c|%C{1}] %m%n";
//        console.setLayout(new PatternLayout(PATTERN));
//        console.setThreshold(Level.ALL);
//        console.activateOptions();
//        //add appender to any Logger (here is root)
//        Logger.getRootLogger().addAppender((Appender)console);
//
//        FileAppender fa = new FileAppender();
//        fa.setName("FileLogger");
////        fa.setFile("test.log");
//        fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
//        fa.setThreshold(Level.DEBUG);
////        fa.setAppend(true);
//        fa.activateOptions();
//
//        //add appender to any Logger (here is root)
//        Logger.getRootLogger().addAppender((Appender)fa);
//        //repeat with all other desired appenders
//    }

    @Test
    @Transactional
    @Rollback
    public void testPostingAReview() throws Exception
    {
        String title = "Gremlins";
        int year = 1984;
        String reviewer = "Hercules Mulligan";
        String comment = "I loved it!";
        float starRating = 3.0f;
        String jsonString = "{\n" +
                String.format("  \"title\": \"%s\",%n", title) +
                String.format("  \"year\": %d,%n", year) +
                String.format("  \"reviewer\": \"%s\",%n", reviewer) +
                String.format("  \"comment\": \"%s\",%n", comment) +
                String.format("  \"starRating\": %f%n", starRating) +
                "}";

        MockHttpServletRequestBuilder request = post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        /**
         * NOTE: I am not sure why it won't let me continue testing with mvc.perform.
         */

        ResponseEntity<MovieReviewResult> response = new MovieReviewsController(service, repository).createMovieReview(new MovieReviewRequest(title, year, reviewer, comment, starRating));

        assertNotNull(response, "Response is null.");
        this.mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.movie.title", equalTo("Gremlins")))
                .andExpect(jsonPath("$.review.reviewer", equalTo("Hercules Mulligan")));


    }
}
