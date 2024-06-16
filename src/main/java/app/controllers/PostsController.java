package app.controllers;

import app.dtos.post.CreatePostDto;
import app.dtos.post.PostDto;
import app.dtos.post.UpdatePostDto;
import app.models.Post;
import app.repositories.PostRepository;
import app.services.ImageService;
import app.services.PostService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostsController {
    private static final Logger logger = LoggerFactory.getLogger(PostsController.class);
    private final PostService postService;
    private final PostRepository postRepository;
    private final ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDto createPostDto) {
        Post post = postService.createPost(createPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") Integer postId){
        var post = postRepository.findById(postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post.get());
    }

    @PutMapping("/edit/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer postId, @RequestBody UpdatePostDto updatePostDto) throws NotFoundException {
        Post post = postService.updatePost(postId, updatePostDto);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getUserPosts(@PathVariable Integer userId) {
        List<PostDto> posts = postService.getUserPosts(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/favorites/{userId}")
    public ResponseEntity<List<PostDto>> getUserFavoritePosts(@PathVariable Integer userId) {
        List<PostDto> favoritePosts = postService.getUserFavoritePosts(userId);
        return ResponseEntity.ok(favoritePosts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/all/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Integer categoryId) {
        List<PostDto> posts = postService.findAllPostsByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }
}
