package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.PostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/edit/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer postId,@RequestBody UpdatePostDto updatePostDto) throws NotFoundException {
        Post post = postService.updatePost(postId, updatePostDto);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Integer userId) {
        List<Post> posts = postService.getUserPosts(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/user-favorites/{userId}")
    public ResponseEntity<List<Post>> getUserFavoritePosts(@PathVariable Integer userId) {
        List<Post> favoritePosts = postService.getUserFavoritePosts(userId);
        return ResponseEntity.ok(favoritePosts);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/all/{categoryId}")
    public ResponseEntity<List<Post>> getAllPostsByCategoryId(@PathVariable Integer categoryId) {
        List<Post> posts = postService.findAllPostsByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }
    @Operation(summary= "Gets post by ID")
    @GetMapping("/get/{postId}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Integer postId)  {
        Optional<Post> post = postService.getPostByPostId(postId);
        return ResponseEntity.ok().body(post);
    }
//    @PutMapping("/add-photos")
//deleting -. inactive/active
}
