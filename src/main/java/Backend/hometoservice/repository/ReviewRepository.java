package Backend.hometoservice.repository;

import Backend.hometoservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<List<Review>> findAllByReviewedId(int reviewedId);
    List<Review> findAllByReviewedId(Integer userId);
}
