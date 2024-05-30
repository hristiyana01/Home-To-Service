package Backend.hometoservice.repository;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    //Optional<Image> findByName(String name);
    List<Image> findAllByPostId(Integer postId);
    Optional<Image> findByName(String name);

}