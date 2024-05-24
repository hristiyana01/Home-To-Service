package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;
//import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Backend.hometoservice.service.ReviewService;

import java.util.List;

@Controller
@RequestMapping("/reviews")
@AllArgsConstructor
@RestController
@Validated
public class ReviewsController {

    private final ReviewService reviewService;
    @PostMapping("/create")
    public Review createReview( @RequestBody CreateReviewDto reviewDto) {
        Review review = reviewService.createReview(reviewDto);
        return review;
    }
    @GetMapping("/get-user-reviews/{reviewedId}")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable int reviewedId) {
        List<Review> userReviews = reviewService.getAllReviewsForUser(reviewedId);
        return ResponseEntity.ok(userReviews);
    }
}
