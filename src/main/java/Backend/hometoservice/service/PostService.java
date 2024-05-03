package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Post;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(CreatePostDto createPostDto);
    List<CreatePostDto> findAllPosts();
    List<Post> getUserPosts(Integer userId);
    List<Post> getUserFavoritePosts(Integer userId);
    Post updatePost(Integer postId, UpdatePostDto updatePostDto) throws NotFoundException;
    Optional<List<Post>> findAllPostsByCategoryId(Integer categoryId);
}
