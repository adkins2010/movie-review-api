package com.example.repository;
import com.example.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface MovieReviewRepository extends CrudRepository<Review, Long> {
}
