package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;

public interface ReviewService {
    Review createReview(CreateReviewDto reviewDto);
}
