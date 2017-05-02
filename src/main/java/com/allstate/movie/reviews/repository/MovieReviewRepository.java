package com.allstate.movie.reviews.repository;

import com.allstate.movie.reviews.model.MovieReview;
import org.springframework.data.repository.CrudRepository;

public interface MovieReviewRepository extends CrudRepository<MovieReview, Long> {
}
