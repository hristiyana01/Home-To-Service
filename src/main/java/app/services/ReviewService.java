package app.services;

import app.dtos.CreateReviewDto;
import app.dtos.ReviewDto;
import app.models.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewDto reviewDto);
    List<ReviewDto> getUserReviews(Integer userId);
}
