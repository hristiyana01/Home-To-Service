package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.repository.CommentRepository;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImplementation implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(CreateCommentDto createCommentDto) {
        Comment comment = Comment.builder()
                .postId(createCommentDto.getPostId())
                .commentText(createCommentDto.getCommentText())
                .userId(createCommentDto.getUserId())
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

    @Override
    public Optional<Comment> getCommentById(Integer commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getPostsComments(Integer postId) {
        Optional<List<Comment>> postCommentsOptional = commentRepository.findCommentsByPostId(postId);
        if(postCommentsOptional.isEmpty()) {
            return new ArrayList<Comment>();
        }

        return postCommentsOptional.get();
    }
}
