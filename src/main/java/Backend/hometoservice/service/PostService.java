package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(CreatePostDto createPostDto);
    List<Post> getUserPosts(Integer userId);
    List<Post> getUserFavoritePosts(Integer userId);

}
