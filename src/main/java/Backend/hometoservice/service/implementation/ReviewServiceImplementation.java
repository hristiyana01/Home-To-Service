package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;
import Backend.hometoservice.repository.ReviewRepository;
import Backend.hometoservice.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewDto reviewDto) {
        Review review = Review.builder()
                .rating(reviewDto.getRating())
                .reviewerId(reviewDto.getReviewerId())
                .reviewedId(reviewDto.getReviewedUserId())
                .date(Instant.now())
                .build();
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviewsForUser(int reviewedId) {
        Optional<List<Review>> userReviews = reviewRepository.findAllByReviewedId(reviewedId);
        return userReviews.orElseGet(ArrayList::new);
    }
}
