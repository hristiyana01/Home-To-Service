package Backend.hometoservice.repository;

import Backend.hometoservice.model.Post;
import Backend.hometoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<List<Post>> findPostsByUserId(Integer userId);

    Optional<List<Post>> findPostsByCategoryId(Integer categoryId);
}
