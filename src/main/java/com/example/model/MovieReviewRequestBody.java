package com.example.model;


public class MovieReviewRequestBody {

    private String title;

    private long year;

    private String reviewer;

    private String comment;

    private long starRating;

    public String getTitle() {
        return title;
    }

    public MovieReviewRequestBody setTitle(String title) {
        this.title = title;
        return this;
    }

    public long getYear() {
        return year;
    }

    public MovieReviewRequestBody setYear(long year) {
        this.year = year;
        return this;
    }

    public String getReviewer() {
        return reviewer;
    }

    public MovieReviewRequestBody setReviewer(String reviewer) {
        this.reviewer = reviewer;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public MovieReviewRequestBody setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public long getStarRating() {
        return starRating;
    }

    public MovieReviewRequestBody setStarRating(long starRating) {
        this.starRating = starRating;
        return this;
    }
}
