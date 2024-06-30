package app.services.implementations;

import app.dtos.CreateReviewDto;
import app.dtos.ReviewDto;
import app.models.Review;
import app.models.User;
import app.repositories.ReviewRepository;
import app.repositories.UserRepository;
import app.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private static final int minRating = 0;
    private static final int maxRating = 5;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

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
        List<User> reviewers = userRepository.findAllById(reviews.stream().map(review -> review.getReviewerId()).collect(Collectors.toList()));

        return reviews.stream()
                .map(review -> {
                   User reviewer = reviewers.stream().filter(user -> Objects.equals(user.getId(), review.getReviewerId())).findFirst().orElseThrow();
                    ReviewDto reviewDto = new ReviewDto();
                    reviewDto.setRating(review.getRating());
                    reviewDto.setUsername(reviewer.getUsername());
                    reviewDto.setReviewerId(reviewer.getId());
                    return reviewDto;
                })
                .collect(Collectors.toList());
    }
}
