package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;
import Backend.hometoservice.repository.ReviewRepository;
import Backend.hometoservice.service.ReviewService;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private static int minRating = 0;
    private static int maxRating = 5;

    @Override
    public Review createReview(CreateReviewDto reviewDto) {
        double roundedRating = Math.round(reviewDto.getRating() * 2) / 2.0;

        if(roundedRating > maxRating || roundedRating < minRating){
            throw new IllegalArgumentException(String.format("Rating needs to be between %d and %d", minRating, maxRating));
        }

        Review review = Review.builder()
                .rating(roundedRating)
                .reviewerId(reviewDto.getReviewerId())
                .reviewedId(reviewDto.getReviewedUserId())
                .date(Instant.now())
                .build();
        reviewRepository.save(review);
        return review;
    }

    @Override
    public List<Review> getAllReviewsForUser(int reviewedId) {
        Optional<List<Review>> userReviews = reviewRepository.findAllByReviewedId(reviewedId);
        return userReviews.orElseGet(ArrayList::new);
    }
}
