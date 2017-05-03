package com.allstate.movie.reviews.model;

import java.io.Serializable;

/**
 * Created by localadmin on 5/2/17.
 */
public class MovieReviewRequest {

    private String title;
    private int year;
    private String reviewer;
    private String comment;
    private float starRating;

    public MovieReviewRequest() {
    }

    /**
     *
     * @param title
     * @param year
     * @param reviewer
     * @param comment
     * @param starRating
     */
    public MovieReviewRequest(String title, int year, String reviewer, String comment, float starRating) {
        this.title = title;
        this.year = year;
        this.reviewer = reviewer;
        this.comment = comment;
        this.starRating = starRating;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return reviewer
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     *
     * @param reviewer
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     *
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return star rating
     */
    public float getStarRating() {
        return starRating;
    }

    /**
     *
     * @param starRating
     */
    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }
}
