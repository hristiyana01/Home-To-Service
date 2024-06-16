package app.controllers;

import app.dtos.CreateReviewDto;
import app.dtos.ReviewDto;
import app.models.Review;
import app.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewsController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody CreateReviewDto reviewDto) {
        Review review = reviewService.createReview(reviewDto);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReviewDto>> getUserReviews(@PathVariable("userId") Integer userId) {
        List<ReviewDto> reviews = reviewService.getUserReviews(userId);
        return ResponseEntity.ok(reviews);
    }
}
