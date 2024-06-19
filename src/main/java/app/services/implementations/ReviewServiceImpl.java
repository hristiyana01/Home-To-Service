package app.services.implementations;

import app.dtos.CreateReviewDto;
import app.dtos.ReviewDto;
import app.models.Review;
import app.repositories.ReviewRepository;
import app.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private static final int minRating = 0;
    private static final int maxRating = 5;
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewDto reviewDto) {
        double roundedRating = Math.round(reviewDto.getRating() * 2) / 2.0;

        if (roundedRating > maxRating || roundedRating < minRating) {
            throw new IllegalArgumentException(String.format("Rating needs to be between %d and %d", minRating, maxRating));
        }

        Review review = Review.builder()
                .rating(roundedRating)
                .reviewerId(reviewDto.getReviewerId())
                .reviewedUserId(reviewDto.getReviewedUserId())
                .date(Instant.now())
                .build();

        reviewRepository.save(review);
        return review;
    }
    @Override
    public List<ReviewDto> getUserReviews(Integer userId) {
        List<Review> reviews = reviewRepository.findByReviewedUserId(userId).get();

        return reviews.stream()
                .map(review -> {
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setRating(review.getRating());
                    return reviewDto;
                })
                .collect(Collectors.toList());
    }
}
