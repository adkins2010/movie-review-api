package com.allstate.movie.reviews.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "movie",
        "review"
})
public class MovieReviewResult {

    @JsonProperty("movie")
    private Movie movie;
    @JsonProperty("review")
    private MovieReview review;

    /**
     *
     */
    public MovieReviewResult() {
    }

    /**
     *
     * @param movie
     * @param review
     */
    public MovieReviewResult(Movie movie, MovieReview review) {
        this.movie = movie;
        this.review = review;
    }

    /**
     *
     * @return movie
     */
    @JsonProperty("movie")
    public Movie getMovie() {
        return movie;
    }

    /**
     *
     * @param movie
     */
    @JsonProperty("movie")
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     *
     * @return review
     */
    @JsonProperty("review")
    public MovieReview getReview() {
        return review;
    }

    /**
     *
     * @param review
     */
    @JsonProperty("review")
    public void setReview(MovieReview review) {
        this.review = review;
    }

}
