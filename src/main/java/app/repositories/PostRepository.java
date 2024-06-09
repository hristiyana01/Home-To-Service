package app.repositories;

import app.enums.Status;
import app.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<List<Post>> findPostsByUserId(Integer userId);

    Optional<List<Post>> findAllByCategoryId(Integer categoryId);

    List<Post> findAllByUserIdAndStatus(Integer userId, Status status);
}
