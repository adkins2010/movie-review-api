package com.example.controller;

import com.example.model.Movie;
import com.example.model.MovieReviewRequestBody;
import com.example.model.MovieReviewResponseBody;
import com.example.model.Review;
import com.example.repository.MovieReviewRepository;
import com.example.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private MovieService movieService;
    private final MovieReviewRepository movieReviewRepository;

    public ReviewController(MovieReviewRepository movieReviewRepository, MovieService movieService) {
        this.movieReviewRepository = movieReviewRepository;
        this.movieService = movieService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<MovieReviewResponseBody> createReview(@RequestBody MovieReviewRequestBody requestBody) {

        Movie movie = this.movieService.withMovieTitle(requestBody.getTitle(), requestBody.getYear());

        System.out.println("movie?!?! : " + movie);

        if (movie==null) {
            movie = this.movieService.postNewMovie(requestBody.getTitle(), requestBody.getYear());
        }

        MovieReviewResponseBody responseBody = new MovieReviewResponseBody();
        responseBody.setMovie(movie);
        Review review = new Review();
        review.setComment(requestBody.getComment());
        review.setMovieId(movie.getId());
        review.setReviewer(requestBody.getReviewer());
        review.setStarRating(requestBody.getStarRating());
        responseBody.setReview(review);

        movieReviewRepository.save(review);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

}
