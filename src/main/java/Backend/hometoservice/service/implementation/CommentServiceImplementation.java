package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateComment;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.repository.CommentRepository;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

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
                .updatedDate(Instant.now())
                .build();
         commentRepository.save(comment);
         return comment;
    }

    @Override
    public void deleteById(Integer commentId) {
        if(!(commentRepository.existsById(commentId))) {
            System.out.println("Comment doesn't exist!");
        }
        commentRepository.deleteById(commentId);

        //TODO: implement message for deleting non-existent comment
    }

    @Override
    public Comment editById(Integer commentId, String commentText) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()) {
            throw new IllegalArgumentException("Comment not found by the provided id.");
        }
        Comment comment = commentOptional.get();
        comment.setCommentText(commentText);
        comment.setUpdatedDate(Instant.now());
        commentRepository.save(comment);
        return comment;
    }
}
