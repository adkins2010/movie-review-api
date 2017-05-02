package com.allstate.compozed.controller;

import com.allstate.compozed.domain.Search;
import com.allstate.compozed.domain.SearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by localadmin on 3/17/17.
 */

@RestController
@RequestMapping("/")

public class MovieController {
    @GetMapping("/movies")
    public List<Search> getMovies(@RequestParam String q) {

        RestTemplate restTemplate = new RestTemplate();
        String movieResourceUrl = "http://www.omdbapi.com/?s=";
        SearchResponse response = restTemplate.getForObject(movieResourceUrl + q,SearchResponse.class);
        return response.getSearch();
    }

}
