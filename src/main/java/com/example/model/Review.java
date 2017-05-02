package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long movieId;

    private String reviewer;

    private String comment;

    private double starRating;

    public Long getId() {
        return id;
    }

    public Review setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Review setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    public String getReviewer() {
        return reviewer;
    }

    public Review setReviewer(String reviewer) {
        this.reviewer = reviewer;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Review setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public double getStarRating() {
        return starRating;
    }

    public Review setStarRating(double starRating) {
        this.starRating = starRating;
        return this;
    }
}
