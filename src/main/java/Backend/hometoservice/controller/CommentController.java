package Backend.hometoservice.controller;


import Backend.hometoservice.dto.CreateComment;
import Backend.hometoservice.dto.UpdateCommentDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.repository.CommentRepository;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/create")
    public String createComment(@ModelAttribute("comment") CreateComment createComment, Model model) {
        Comment comment = commentService.createComment(createComment);
       model.addAttribute("comment", comment);
       return "comment-created";
    }
    @PutMapping("/edit/{commentId}")
    public ResponseEntity<UpdateCommentDto> editComment(@PathVariable Integer commentId, @ModelAttribute UpdateCommentDto updateComment) {
        commentService.editById(commentId, updateComment.getCommentText());
        return ResponseEntity.ok(updateComment);
    }

//    @GetMapping("/getAll/{postId}")
//    public String getAllPostComments()

    @DeleteMapping("/delete-by-id/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId) {
      commentService.deleteById(commentId);
      return ResponseEntity.ok().build();
    }
}
