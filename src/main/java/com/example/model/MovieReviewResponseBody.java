package com.example.model;

public class MovieReviewResponseBody {

    private Movie movie;

    private Review review;


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
