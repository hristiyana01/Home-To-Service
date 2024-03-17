package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;
import Backend.hometoservice.repository.ReviewRepository;
import Backend.hometoservice.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewDto reviewDto) {
        Review review = Review.builder()
                .rating(reviewDto.getRating())
                .date(Instant.now())
                .reviewedId(reviewDto.getReviewedUserId())
                .reviewerId(reviewDto.getReviewerId())
                .build();
        return reviewRepository.save(review);
    }
}
