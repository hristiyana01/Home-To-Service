package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewDto reviewDto);
    List<Review> getAllReviewsForUser(int reviewedId);
}
