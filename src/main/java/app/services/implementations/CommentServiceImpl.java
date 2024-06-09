package app.services.implementations;

import app.dtos.CommentDto;
import app.dtos.CreateCommentDto;
import app.models.Comment;
import app.repositories.CommentRepository;
import app.repositories.UserRepository;
import app.services.CommentService;
import app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(CreateCommentDto createCommentDto) {
        Comment comment = Comment.builder()
                .postId(createCommentDto.getPostId())
                .commentText(createCommentDto.getText())
                .userId(createCommentDto.getUserId())
                .createdAt(Instant.now())
                .build();

        commentRepository.save(comment);
        return comment;
    }
    @Override
    public List<CommentDto> findCommentsByPostId(Integer postId) {
        Optional<List<Comment>> comments = commentRepository.findCommentsByPostId(postId);
        return comments.orElseThrow(() -> new RuntimeException("No comments found for post id: " + postId))
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment) {
        String username = userRepository.findById(comment.getUserId()).get().getUsername();
        return CommentDto.builder()
                .postId(comment.getPostId())
                .commentText(comment.getCommentText())
                .username(username)
                .build();
    }
}
