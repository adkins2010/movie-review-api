package com.example.service;

import com.example.model.Movie;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.attribute.standard.Media;
import java.util.Collections;

@Service
public class MovieService {

    private final RestTemplate template = new RestTemplate();
    private final String AUTH_BEARER = "Bearer 18394928-6684-43ca-9026-e19ed3028b17";
    private final String API_URL = "http://localhost:9090/movies/";


    public Movie withMovieTitle(String movieTitle, Long year) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTH_BEARER);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL + "find")
                .queryParam("title", movieTitle)
                .queryParam("year", year);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Movie> exchange = template.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    Movie.class);
            return exchange.getBody();
        } catch (HttpClientErrorException e) {

        }

        return null;
    }

    public Movie postNewMovie(String movieTitle, Long year) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", AUTH_BEARER);
            headers.setContentType(MediaType.APPLICATION_JSON);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL);
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

            body.add("title", movieTitle);
            body.add("year", year.toString());

            HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
//            ResponseEntity<Movie> exchange = template.exchange(
//                    builder.build().encode().toUri(),
//                    HttpMethod.POST,
//                entity,
//                Movie.class);
        try {
          //  ResponseEntity<Movie> movie = template.exchange(API_URL, HttpMethod.POST, entity, Movie.class);
                        ResponseEntity<Movie> movie = template.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.POST,
                entity,
                Movie.class);
            return movie.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("--------- " + e);
        }

        return null;
    }

    public RestTemplate getRestTemplate() {
        return template;
    }
}
