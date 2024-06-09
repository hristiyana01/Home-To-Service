package app.controllers;

import app.dtos.CommentDto;
import app.dtos.CreateCommentDto;
import app.models.Comment;
import app.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentDto createCommentDto) {
        Comment comment = commentService.createComment(createCommentDto);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") Integer postId) {
        List<CommentDto> postComments = commentService.findCommentsByPostId(postId);
        return ResponseEntity.ok(postComments);
    }
}
