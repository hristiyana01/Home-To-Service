package app.services;

import app.dtos.CommentDto;
import app.dtos.CreateCommentDto;
import app.models.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(CreateCommentDto createCommentDto);
    List<CommentDto> findCommentsByPostId(Integer postId);
}
