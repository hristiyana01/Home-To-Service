package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.model.Comment;

public interface CommentService {
    Comment createComment(CreateCommentDto createCommentDto);
    void deleteById(Integer commentId);
    Comment editById(Integer commentId, String commentText);
}
