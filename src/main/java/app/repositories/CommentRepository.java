package app.repositories;

import app.dtos.CommentDto;
import app.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPostId(Integer postId);

    Optional<List<Comment>> findCommentsByPostId(Integer postId);
}
