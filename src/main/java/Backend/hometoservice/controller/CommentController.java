package Backend.hometoservice.controller;


import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.dto.UpdateCommentDto;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentDto createCommentDto) {
        Comment comment = commentService.createComment(createCommentDto);
        return ResponseEntity.ok(comment);
    }
    @PutMapping("/edit/{commentId}")
    public ResponseEntity<String> editComment(@PathVariable Integer commentId, @RequestBody UpdateCommentDto updateComment) {
        commentService.editById(commentId, updateComment.getCommentText());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-by-id/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get/{commentId}")
    public Optional<Comment> getCommentById(@PathVariable Integer commentId) {
        return commentService.getCommentById(commentId);
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<Comment>> getAllComments() {
        var comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
//    @GetMapping("/get-all-by-post/{postId}")
//    public ResponseEntity<List<Comment>> getPostComments(@PathVariable Integer postId) {
//        List<Comment> comments = commentService.getPostsComments(postId);
//        return ResponseEntity.ok(comments);
//    }
}
