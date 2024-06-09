package app.repositories;

import app.dtos.ReviewDto;
import app.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<List<Review>> findByReviewedUserId(Integer reviewedUserId);
}

