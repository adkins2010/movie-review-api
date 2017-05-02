package com.example.service;


import com.example.model.Movie;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class MovieServiceTest {
    private final MovieService service = new MovieService();

    @Test
    public void callsMockRestTemplateWithTitleAndReturnsAList() throws Exception {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(service.getRestTemplate());

        mockServer.expect(requestTo(stringContainsInOrder(Arrays.asList("http://localhost:9090/movies/find?", "title=Zach%20Movie&year=2017"))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getJSON("/ZachMovieResponse.json"), MediaType.APPLICATION_JSON));

        Movie movieReviewResults = service.withMovieTitle("Zach Movie",2017L);
        System.out.println("movieReviewResults:" + movieReviewResults.getId());

        assertEquals(movieReviewResults.getId(), 6L);
        assertEquals(movieReviewResults.getTitle(), "Zach Movie");
        assertEquals(movieReviewResults.getYear(), 2017L);

        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }


}
