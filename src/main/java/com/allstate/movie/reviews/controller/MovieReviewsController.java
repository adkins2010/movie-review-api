package com.allstate.movie.reviews.controller;

import com.allstate.movie.reviews.model.Movie;
import com.allstate.movie.reviews.model.MovieReview;
import com.allstate.movie.reviews.model.MovieReviewRequest;
import com.allstate.movie.reviews.model.MovieReviewResult;
import com.allstate.movie.reviews.repository.MovieReviewRepository;
import com.allstate.movie.reviews.services.MovieSearchService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author localadmin
 * @version 5/2/17.
 */
@RestController
@RequestMapping("/reviews")
public class MovieReviewsController {

    private final MovieSearchService service;
    private MovieReviewRepository repository;

    /**
     *
     * @param service
     * @param repository
     */
    public MovieReviewsController(MovieSearchService service, MovieReviewRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    @PostMapping("")
    public ResponseEntity<MovieReviewResult> createMovieReview (@RequestBody MovieReviewRequest request) {
        ResponseEntity<MovieReviewResult> response = new ResponseEntity<MovieReviewResult>(HttpStatus.CONFLICT);
        Movie movie = service.searchByTitleAndYear(request.getTitle(), request.getYear());
        if(movie != null) {
            MovieReview review = new MovieReview(request.getReviewer(), request.getComment(), (double) request.getStarRating());
            try {
                review = repository.save(review);
            } catch (Exception e) {
                Logger.getLogger(this.getClass()).log(Level.ERROR, e.getMessage(), e);
            }
            if(review != null) {
                MovieReviewResult reviewResult = new MovieReviewResult(movie, review);
                try {
                    response = new ResponseEntity<>(reviewResult, HttpStatus.CREATED);
                } catch (Exception e) {
                    Logger.getLogger(this.getClass()).log(Level.ERROR, e.getMessage(), e);
                }
            }
            else {
                Logger.getLogger(this.getClass().getName()).error("Review was not saved!!!!");
            }
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

}
