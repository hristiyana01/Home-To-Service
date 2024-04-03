package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateComment;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.repository.CommentRepository;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.Instant;

@Service
@AllArgsConstructor
public class CommentServiceImplementation implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(CreateComment createComment) {
        Comment comment = Comment.builder()
                .postId(createComment.getPostId())
                .commentText(createComment.getCommentText())
                .userId(createComment.getUserId())
                .createdAt(Instant.now())
                .build();
         commentRepository.save(comment);
         return comment;
    }
    @DeleteMapping("/delete-by-id/{commentId}")
    public Comment deleteComment    
}
