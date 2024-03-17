package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.model.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import Backend.hometoservice.service.ReviewService;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/reviews")
@AllArgsConstructor
@RestController
public class ReviewsController {

    private final ReviewService reviewService;
    @PostMapping("/create")
    public Review createReview(CreateReviewDto reviewDto) {
        Review review = reviewService.createReview(reviewDto);
        return review;
    }
}
