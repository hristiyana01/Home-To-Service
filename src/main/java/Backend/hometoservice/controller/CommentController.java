package Backend.hometoservice.controller;


import Backend.hometoservice.dto.CreateComment;
import Backend.hometoservice.model.Comment;
import Backend.hometoservice.repository.CommentRepository;
import Backend.hometoservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CreateComment createComment) {
        Comment comment = commentService.createComment(createComment);
        return ResponseEntity.ok(comment);
    }
}
