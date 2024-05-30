package Backend.hometoservice.controller;


import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.dto.UpdateCommentDto;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
