package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreateComment;
import Backend.hometoservice.model.Comment;

public interface CommentService {
    Comment createComment(CreateComment createComment);
    void deleteById(Integer commentId);
    Comment editById(Integer commentId, String commentText);
}