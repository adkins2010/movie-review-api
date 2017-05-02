package com.example.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.gson.*;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieReviewIntegrationTest {

    @Autowired
    TestRestTemplate template;

    private final String AUTH_BEARER = "Bearer 18394928-6684-43ca-9026-e19ed3028b17";;

    @Test
    public void returnsMovieReview() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("title", "Gremlins");
        requestBody.addProperty("year", 1984);
        requestBody.addProperty("reviewer", "Hercules Mulligan");
        requestBody.addProperty("comment", "I loved it!");
        requestBody.addProperty("starRating", 3.0);

        String path = "/reviews";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTH_BEARER);
        headers.setContentType(MediaType.APPLICATION_JSON);


        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(requestBody);
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = template.exchange(path, HttpMethod.POST, request, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        JsonObject resultObjects = new JsonParser().parse(response.getBody()).getAsJsonObject();

        assertEquals("Will return one movie and one review so will have size of 2", 2, resultObjects.size());
        JsonObject result = resultObjects.getAsJsonObject();
        System.out.println(result);
        assertThat(result.get("movie").getAsJsonObject().get("title").getAsString(), equalTo("Gremlins"));
        assertThat(result.get("movie").getAsJsonObject().get("year").getAsString(), equalTo("1984"));
        assertThat(result.get("review").getAsJsonObject().get("reviewer").getAsString(), equalTo("Hercules Mulligan"));
        assertThat(result.get("review").getAsJsonObject().get("comment").getAsString(), equalTo("I loved it!"));
        assertThat(result.get("review").getAsJsonObject().get("starRating").getAsString(), equalTo("3.0"));
    }

    @Test
    public void returnsNewlyCreatedMovieAndReviewIfNotFound() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("title", "Movie123");
        requestBody.addProperty("year", 1900);
        requestBody.addProperty("reviewer", "Awesome person");
        requestBody.addProperty("comment", "It was terribad");
        requestBody.addProperty("starRating", 0.0);

        String path = "/reviews";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTH_BEARER);
        headers.setContentType(MediaType.APPLICATION_JSON);


        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(requestBody);
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = template.exchange(path, HttpMethod.POST, request, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        JsonObject resultObjects = new JsonParser().parse(response.getBody()).getAsJsonObject();

        assertEquals("Will return one movie and one review so will have size of 2", 2, resultObjects.size());
        JsonObject result = resultObjects.getAsJsonObject();
        System.out.println(result);
        assertThat(result.get("movie").getAsJsonObject().get("title").getAsString(), equalTo("Movie123"));
        assertThat(result.get("movie").getAsJsonObject().get("year").getAsString(), equalTo("1900"));
        assertThat(result.get("review").getAsJsonObject().get("reviewer").getAsString(), equalTo("Awesome person"));
        assertThat(result.get("review").getAsJsonObject().get("comment").getAsString(), equalTo("It was terribad"));
        assertThat(result.get("review").getAsJsonObject().get("starRating").getAsString(), equalTo("0.0"));
    }
}
