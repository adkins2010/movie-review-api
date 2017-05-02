package com.allstate.movie.reviews.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "reviewer",
        "comment",
        "starRating"
})
@Entity
public class MovieReview implements Serializable {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("reviewer")
    private String reviewer;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("starRating")
    private Double starRating;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;


    /**
     *
     */
    public MovieReview() {
    }

    /**
     *
     * @param reviewer
     * @param comment
     * @param starRating
     */
    public MovieReview(String reviewer, String comment, Double starRating) {
        this.reviewer = reviewer;
        this.comment = comment;
        this.starRating = starRating;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("reviewer")
    public String getReviewer() {
        return reviewer;
    }

    @JsonProperty("reviewer")
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("starRating")
    public Double getStarRating() {
        return starRating;
    }

    @JsonProperty("starRating")
    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Transient
    @JsonProperty("title")
    public String getTitle() {
        return this.movie.getTitle();
    }

    public void setTitle(String title) {
        this.movie.setTitle(title);
    }

    @Transient
    @JsonProperty("year")
    public Integer getYear() {
        return this.movie.getYear();
    }

    public void setYear(Integer year) {
        this.movie.setYear(year);
    }
}