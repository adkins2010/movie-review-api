package com.allstate.movie.reviews.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by localadmin on 5/2/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieReviewsIntegrationTest {
    @Autowired
    TestRestTemplate template;

    @Test
    public void postsReview () throws UnsupportedEncodingException {

        String path = "/reviews";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonString = "{\n" +
                "  \"title\": \"Gremlins\",\n" +
                "  \"year\": 1984,\n" +
                "  \"reviewer\": \"Hercules Mulligan\",\n" +
                "  \"comment\": \"I loved it!\",\n" +
                "  \"starRating\": 3.0\n" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = template.exchange(path, HttpMethod.POST, request, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }

    @Test
    public void getReviews () throws UnsupportedEncodingException {
        JsonObject requestBody = new JsonObject();
        String path = "http://localhost:9090/movies/1984/Gremlins";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer 18394928-6684-43ca-9026-e19ed3028b17");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(requestBody);
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = template.exchange(path, HttpMethod.GET, request, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        JsonObject result = new JsonParser().parse(response.getBody()).getAsJsonObject();

        assertThat(result.get("title").getAsString(), equalTo("Gremlins"));
        assertThat(result.get("year").getAsString(), equalTo("1984"));
        assertThat(result.get("id").getAsString(), equalTo("5"));
    }

}
