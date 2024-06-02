package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(CreateCommentDto createCommentDto);
    void deleteById(Integer commentId);
    Comment editById(Integer commentId, String commentText);
    Optional<Comment> getCommentById(Integer commentId);
    List<Comment> getAllComments();
    List<Comment> getPostsComments(Integer postId);
}
