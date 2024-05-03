package Backend.hometoservice.controller;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.service.PostService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createPost(@ModelAttribute("post") CreatePostDto createPostDto, Model model) {
        Post post = postService.createPost(createPostDto);
        model.addAttribute("post", post);
        return "post-created"; //името на html файла за изглед
    }
    @GetMapping("/user/{userId}")
    public String getUserPosts(@PathVariable Integer userId, Model model) {
        List<Post> posts = postService.getUserPosts(userId);
        model.addAttribute("posts", posts);
        return "user-posts";
    }
    @GetMapping("/{userId}/favourites")
    public String getUserFavoritePosts(@PathVariable Integer userId, Model model) {
        List<Post> favoritePosts = postService.getUserFavoritePosts(userId);
        model.addAttribute("favouritePosts", favoritePosts);
        return "user-favourite-posts";
    }
    @PutMapping("/edit/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Integer postId,@ModelAttribute UpdatePostDto updatePostDto) throws NotFoundException {
        postService.updatePost(postId, updatePostDto);
        return ResponseEntity.ok().body("Post updated successfully.");
    }
//    @GetMapping("post/{postId}")
//    public ResponseEntity<String> getPost(@PathVariable Integer postId) {
//        Post post = postService.getUserPost(postId);
//        return ResponseEntity.ok(post)
//    }
    @GetMapping("/all")
    public String getAllPosts(Model model) {
        List<CreatePostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "posts-list";
    }
    @GetMapping("/all/{categoryId}")
    public String getAllPostsByCategoryId(@PathVariable Integer categoryId) {
        Optional<List<Post>> posts = postService.findAllPostsByCategoryId(categoryId);
        return "posts-by-category";
    }
//    @PutMapping("/add-photos")
//deleting -. inactive/active status !!
}
