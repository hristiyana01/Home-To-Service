package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDto createPostDto) {
        Post post = postService.createPost(createPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    @GetMapping("/user-posts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Integer userId) {
        List<Post> posts = postService.getUserPosts(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/user-favorite-posts/{userId}")
    public ResponseEntity<Post> getUserFavoritePosts(@RequestBody CreatePostDto createPostDto) {
        Post post = postService.createPost(createPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

//    @PutMapping("/add-photos")
//deleting -. inactive/active
}
