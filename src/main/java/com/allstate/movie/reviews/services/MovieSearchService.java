package com.allstate.movie.reviews.services;

import com.allstate.movie.reviews.model.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class MovieSearchService {
    private final RestTemplate template = new RestTemplate();
//    private final String NYT_API_KEY = "3167189b55754434823f700c63c8dffc";
    private final String API_URL = "http://localhost:9090/movies";

    /**
     *
     * @param title
     * @param year
     * @return movie found
     */
    public Movie searchByTitleAndYear(String title, int year) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization","Bearer 18394928-6684-43ca-9026-e19ed3028b17");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .path(String.format("/%d/%s", year, title));

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Movie> exchange = template.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                request,
                Movie.class);

        return exchange.getBody();
    }

    /**
     *
     * @param movie
     * @return movie given if it was created, null otherwise.
     */
    public Movie createMovie(Movie movie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization","Bearer 18394928-6684-43ca-9026-e19ed3028b17");
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("title", movie.getTitle());
        requestBody.addProperty("year", movie.getYear());

        Gson builder = new GsonBuilder().create();

        HttpEntity<String> request = new HttpEntity<>(builder.toJson(requestBody), headers);
        ResponseEntity<Movie> exchange = template.exchange(
                API_URL,
                HttpMethod.POST,
                request,
                Movie.class);

        return exchange.getBody();
    }

    public RestTemplate getRestTemplate() {
        return template;
    }
}
